import groovy.util.GroovyTestCase.assertEquals
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import org.junit.Test

internal class MoneyTest {
    @Test
    fun testSumTimes() {
        val fiveDollar = Money.dollar(5)
        val tenFrancs = Money.franc(10)
        val bank = Bank()
        bank.addRate("CHF", "USD", 2)
        val sum = Sum(fiveDollar, tenFrancs).times(2)
        val result = bank.reduce(sum, "USD")
        assertEquals(Money.dollar(20), result)
    }

    @Test
    fun testSumPlusMonetY() {
        val fiveDollar: Expression = Money.dollar(5)
        val tenFrancs: Expression = Money.franc(10)
        val bank = Bank()
        bank.addRate("CHF", "USD", 2)
        val sum = Sum(fiveDollar, tenFrancs).plus(fiveDollar)
        val result = bank.reduce(sum, "USD")
        assertEquals(Money.dollar(15), result)
    }

    @Test
    fun testMixedAddition() {
        val fiveDollar: Expression = Money.dollar(5)
        val tenFrancs: Expression = Money.franc(10)
        val bank = Bank()
        bank.addRate("CHF", "USD", 2)
        val result = bank.reduce(fiveDollar.plus(tenFrancs), "USD")
        assertEquals(Money.dollar(10), result)
    }

    @Test
    fun testReduceMoneyDifferentCurrency() {
        val bank = Bank()
        bank.addRate("CHF", "USD", 2)
        val result = bank.reduce(Money.franc(2), "USD")
        assertEquals(Money.dollar(1), result)
    }

    @Test
    fun testReduceMoney() {
        val bank = Bank()
        val result = bank.reduce(Money.dollar(10), "USD")
        assertEquals(Money.dollar(10), result)
    }

    @Test
    fun testReduceSum() {
        val sum = Sum(Money.dollar(7), Money.dollar(2))
        val bank = Bank()
        val result = bank.reduce(sum, "USD")
        assertEquals(Money.dollar(9), result)
    }

    @Test
    fun testSimpleAddition() {
        val five = Money.dollar(5)
        val sum: Sum = five.plus(five)
        val bank = Bank()
        val reduced = bank.reduce(sum, "USD")
        assertEquals(Money.dollar(10), reduced)
    }

    @Test
    fun testMultiplication() {
        val five = Money.dollar(5)
        assertEquals(Money.dollar(10), five.times(2))
        assertEquals(Money.dollar(15), five.times(3))
    }

    @Test
    fun testEquality() {
        assertTrue(Money.dollar(5) == Money.dollar(5))
        assertFalse(Money.dollar(5) == Money.dollar(6))
        assertFalse(Money.franc(5).equals(Money.dollar(5)))
    }

    @Test
    fun testCurrency() {
        assertEquals("USD", Money.dollar(1).currency())
        assertEquals("CHF", Money.franc(1).currency())
    }
}
