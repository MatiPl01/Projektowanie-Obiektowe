package pl.edu.agh.internetshop;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Function;

public class Order {
    private static final BigDecimal TAX_VALUE = BigDecimal.valueOf(1.23);
	private final UUID id;
    private final List<Product> products;
    private Customer customer;
    private Shipment shipment;
    private ShipmentMethod shipmentMethod;
    private PaymentMethod paymentMethod;
    private Discount discount = new Discount(0);
    private boolean paid;

    public Order(List<Product> products) {
        this.products = Objects.requireNonNull(products, "Order products cannot be null");
        this.products.forEach(product -> Objects.requireNonNull(product, "Order product cannot be null"));
        id = UUID.randomUUID();
        paid = false;
    }

    public Order(List<Product> products, Customer customer) {
        this(products);
        this.customer = customer;
    }

    public Order(List<Product> products, Discount discount) {
        this(products);
        this.discount = discount;
    }

    public Order(List<Product> products, Discount discount, Customer customer) {
        this(products, discount);
        this.customer = customer;
    }

    public UUID getId() {
        return id;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public BigDecimal getDiscount() { return discount.getValue(); };

    public String getCustomerLastName() { return customer.getLastName(); }

    public BigDecimal getPrice() {
        return discount.applyTo(getPriceWithoutOrderDiscount());
    }

    public BigDecimal getPriceWithoutDiscounts() {
        return getMappedPrice(Product::getPriceWithoutDiscount);
    }

    public BigDecimal getPriceWithoutOrderDiscount() {
        return getMappedPrice(Product::getPrice);
    }

    public BigDecimal getPriceWithTaxes() {
        return getPrice().multiply(TAX_VALUE).setScale(Product.PRICE_PRECISION, RoundingMode.HALF_UP);
    }

    public boolean isSent() {
        return shipment != null && shipment.isShipped();
    }

    public boolean isPaid() { return paid; }

    public Shipment getShipment() {
        return shipment;
    }

    public List<Product> getProducts() {
        return products;
    }

    public ShipmentMethod getShipmentMethod() {
        return shipmentMethod;
    }

    public void setShipmentMethod(ShipmentMethod shipmentMethod) {
        this.shipmentMethod = shipmentMethod;
    }

    public void send() {
        boolean sentSuccessful = getShipmentMethod().send(shipment, shipment.getSenderAddress(), shipment.getRecipientAddress());
        shipment.setShipped(sentSuccessful);
    }

    public void pay(MoneyTransfer moneyTransfer) {
        moneyTransfer.setCommitted(getPaymentMethod().commit(moneyTransfer));
        paid = moneyTransfer.isCommitted();
    }

    public void setShipment(Shipment shipment) {
        this.shipment = shipment;
    }

    private BigDecimal getMappedPrice(Function<Product, BigDecimal> fn) {
        return products.stream()
                .map(fn)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
