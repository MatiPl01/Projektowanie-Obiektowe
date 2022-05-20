package pl.edu.agh.internetshop;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static pl.edu.agh.internetshop.util.CustomAssertions.assertBigDecimalCompareValue;

public class OrderTest {
	private Order getOrderWithMockedProduct() {
		Product product = mock(Product.class);
		return new Order(Collections.singletonList(product));
	}

	@Test
	public void getProductFromOrder() {
		// given
		Product expectedProduct = mock(Product.class);
		Order order = new Order(Collections.singletonList(expectedProduct));

		// when
		Product actualProduct = order.getProducts().iterator().next();

		// then
		assertSame(expectedProduct, actualProduct);
	}

	@Test
	public void getMultipleProductsFromOrder() {
		// given
		Product product1 = mock(Product.class);
		Product product2 = mock(Product.class);

		// when
		Order order = new Order(Arrays.asList(product1, product2));
		List<Product> products = order.getProducts();

		// then
		assertEquals(2, products.size());
		assertSame(product1, products.get(0));
		assertSame(product2, products.get(1));
	}

	@Test
	public void getSetShipment() {
		// given
		Order order = getOrderWithMockedProduct();
		Shipment expectedShipment = mock(Shipment.class);

		// when
		order.setShipment(expectedShipment);

		// then
		assertSame(expectedShipment, order.getShipment());
	}

	@Test
	public void getShipmentWithoutSetting() {
		// given
		Order order = getOrderWithMockedProduct();

		// when

		// then
		assertNull(order.getShipment());
	}

	@Test
	public void getPrice() {
		// given
		BigDecimal expectedOrderPrice = BigDecimal.valueOf(1000);
		Product product = mock(Product.class);
		given(product.getPrice()).willReturn(expectedOrderPrice);
		Order order = new Order(Collections.singletonList(product));

		// when
		BigDecimal actualPrice = order.getPrice();

		// then
		assertBigDecimalCompareValue(expectedOrderPrice, actualPrice);
	}

	private Order getOrderWithCertainProductPrice(double productPriceValue) {
		BigDecimal productPrice = BigDecimal.valueOf(productPriceValue);
		Product product = mock(Product.class);
		given(product.getPrice()).willReturn(productPrice);
		return new Order(Collections.singletonList(product));
	}

	@Test
	public void getPriceWithTaxesWithoutRoundUp() {
		// given

		// when
		Order order = getOrderWithCertainProductPrice(2); // 2 PLN

		// then
		assertBigDecimalCompareValue(order.getPriceWithTaxes(), BigDecimal.valueOf(2.46)); // 2.44 PLN
	}

	@Test
	public void getPriceWithTaxesWithRoundDown() {
		// given

		// when
		Order order = getOrderWithCertainProductPrice(0.01); // 0.01 PLN

		// then
		assertBigDecimalCompareValue(order.getPriceWithTaxes(), BigDecimal.valueOf(0.01)); // 0.01 PLN
	}

	@Test
	public void getPriceWithTaxesWithRoundUp() {
		// given

		// when
		Order order = getOrderWithCertainProductPrice(0.03); // 0.03 PLN

		// then
		assertBigDecimalCompareValue(order.getPriceWithTaxes(), BigDecimal.valueOf(0.04)); // 0.04 PLN
	}

	@Test
	public void getSetShipmentMethod() {
		// given
		Order order = getOrderWithMockedProduct();
		ShipmentMethod surface = mock(SurfaceMailBus.class);

		// when
		order.setShipmentMethod(surface);

		// then
		assertSame(surface, order.getShipmentMethod());
	}

	@Test
	public void getSending() {
		// given
		Order order = getOrderWithMockedProduct();
		SurfaceMailBus surface = mock(SurfaceMailBus.class);
		Shipment shipment = mock(Shipment.class);
		given(shipment.isShipped()).willReturn(true);

		// when
		order.setShipmentMethod(surface);
		order.setShipment(shipment);
		order.send();

		// then
		assertTrue(order.isSent());
	}

	@Test
	public void getIsSentWithoutSending() {
		// given
		Order order = getOrderWithMockedProduct();
		Shipment shipment = mock(Shipment.class);
		given(shipment.isShipped()).willReturn(true);

		// when

		// then
		assertFalse(order.isSent());
	}

	@Test
	public void getWhetherIdExists() {
		// given
		Order order = getOrderWithMockedProduct();

		// when

		// then
		assertNotNull(order.getId());
	}

	@Test
	public void getSetPaymentMethod() {
		// given
		Order order = getOrderWithMockedProduct();
		PaymentMethod paymentMethod = mock(MoneyTransferPaymentTransaction.class);

		// when
		order.setPaymentMethod(paymentMethod);

		// then
		assertSame(paymentMethod, order.getPaymentMethod());
	}

	@Test
	public void getPaying() {
		// given
		Order order = getOrderWithMockedProduct();
		PaymentMethod paymentMethod = mock(MoneyTransferPaymentTransaction.class);
		given(paymentMethod.commit(any(MoneyTransfer.class))).willReturn(true);
		MoneyTransfer moneyTransfer = mock(MoneyTransfer.class);
		given(moneyTransfer.isCommitted()).willReturn(true);

		// when
		order.setPaymentMethod(paymentMethod);
		order.pay(moneyTransfer);

		// then
		assertTrue(order.isPaid());
	}

	@Test
	public void getIsPaidWithoutPaying() {
		// given
		Order order = getOrderWithMockedProduct();

		// when

		// then
		assertFalse(order.isPaid());
	}

	@Test
	public void getPriceWithoutDiscounts() {
		// given
		Product product1 = mock(Product.class);
		Product product2 = mock(Product.class);

		given(product1.getPriceWithoutDiscount()).willReturn(BigDecimal.valueOf(1.1));
		given(product2.getPriceWithoutDiscount()).willReturn(BigDecimal.valueOf(1.2));

		// when
		Order order = new Order(Arrays.asList(product1, product2));

		// then
		assertBigDecimalCompareValue(BigDecimal.valueOf(2.3), order.getPriceWithoutDiscounts());
	}

	@Test
	public void getPriceWithoutOrderDiscount() {
		// given
		Product product1 = mock(Product.class);
		Product product2 = mock(Product.class);

		given(product1.getPrice()).willReturn(BigDecimal.valueOf(1.1));
		given(product2.getPrice()).willReturn(BigDecimal.valueOf(1.2));

		// when
		Order order = new Order(Arrays.asList(product1, product2));

		// then
		assertBigDecimalCompareValue(BigDecimal.valueOf(2.3), order.getPriceWithoutOrderDiscount());
	}

	@Test
	public void getOrderDiscount() {
		// given
		Discount discount = mock(Discount.class);
		Product product = mock(Product.class);

		given(discount.getValue()).willReturn(BigDecimal.valueOf(.1));

		// when
		Order order = new Order(Collections.singletonList(product), discount);

		// then
		assertBigDecimalCompareValue(BigDecimal.valueOf(.1), order.getDiscount());
	}

	@Test
	public void getOrderCustomer() {
		// given
		Customer customer = mock(Customer.class);
		Product product = mock(Product.class);

		// when
		Order order = new Order(Collections.singletonList(product), customer);

		// then
		assertSame(customer, order.getCustomer());
	}

	@Test
	public void getOrderCustomerLastName() {
		// given
		Customer customer = mock(Customer.class);
		Product product = mock(Product.class);

		given(customer.getLastName()).willReturn("Customer");

		// when
		Order order = new Order(Collections.singletonList(product), customer);

		// then
		assertEquals("Customer", order.getCustomerLastName());
	}

	@Test
	public void createOrderWithCustomerAndDiscount() {
		// given
		Customer customer = mock(Customer.class);
		Product product = mock(Product.class);
		Discount discount = mock(Discount.class);

		// when
		Order order = new Order(Collections.singletonList(product), discount, customer);

		// then
		assertSame(customer, order.getCustomer());
		assertSame(product, order.getProducts().get(0));
	}

	@Test
	public void setOrderCustomer() {
		// give
		Customer customer = mock(Customer.class);
		Product product = mock(Product.class);
		Order order = new Order(Collections.singletonList(product));

		// when
		order.setCustomer(customer);

		// then
		assertSame(customer, order.getCustomer());
	}
}
