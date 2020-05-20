open class Money(
        val amount: Int,
        protected val currency: String
) : Expression {
    companion object {
        fun dollar(amount: Int): Money {
            return Money(amount, "USD")
        }

        fun franc(amount: Int): Money {
            return Money(amount, "CHF")
        }
    }

    override fun hashCode(): Int {
        return amount
    }

    override fun equals(other: Any?): Boolean {
        val dollar = other as Money
        return this.amount == dollar.amount && this.currency == dollar.currency
    }

    override fun plus(addend: Expression): Sum {
        return Sum(this, addend)
    }

    override fun times(multiplier: Int): Expression {
        return Money(amount * multiplier, currency)
    }

    override fun reduce(bank: Bank, to: String): Money {
        val rate = bank.rate(currency, to) ?: 2
        return Money(this.amount / rate, to)
    }

    fun currency(): String = currency
}