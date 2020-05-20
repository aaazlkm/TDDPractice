data class RatePair(
        val from: String,
        val to: String
) {
    override fun equals(other: Any?): Boolean {
        val pair = other as RatePair
        return from == pair.from && to == pair.to
    }

    override fun hashCode(): Int {
        return 0
    }
}