abstract class Money(
        protected val amount: Int,
        protected val currency: String
) {
    companion object {
        fun dollar(amount: Int): Money {
            return Dollar(amount, "USD")
        }

        fun franc(amount: Int): Money {
            return Franc(amount, "CHF")
        }
    }

    override fun hashCode(): Int {
        return amount
    }

    override fun equals(other: Any?): Boolean {
        val dollar = other as Money
        return this.amount == other.amount && this.javaClass == other.javaClass
    }

    abstract fun times(multiplier: Int): Money

    fun currency(): String = currency
}