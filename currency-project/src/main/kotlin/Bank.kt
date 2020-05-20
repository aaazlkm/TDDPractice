class Bank {
    private val rates = hashMapOf<RatePair, Int>()
    fun addRate(from: String, to: String, rate: Int) {
        rates[RatePair(from, to)] = rate
    }

    fun reduce(expression: Expression, to: String): Money {
        return expression.reduce(this, to)
    }

    fun rate(from: String, to: String): Int? {
        if (from == to) return 1
        return rates[RatePair(from, to)]
    }
}