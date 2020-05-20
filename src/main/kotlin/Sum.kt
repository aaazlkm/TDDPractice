class Sum (
        val augend: Expression,
        val addend: Expression
): Expression {
    override fun plus(addend: Expression): Expression {
        return Sum(this, addend)
    }

    override fun times(multiplier: Int): Expression {
        return Sum(augend.times(multiplier), addend.times(multiplier))
    }

    override fun reduce(bank: Bank, to: String): Money {
        return Money(augend.reduce(bank, to).amount + addend.reduce(bank, to).amount, to)
    }
}