package me.y9san9.ksm.logger

public interface Logger {
    public operator fun get(tag: String): Logger
    public fun pop(): Logger

    public fun log(
        vararg values: Any?,
        separator: String = " ",
        end: String = "\n"
    )

    public companion object : Logger by Default()

    public class Default(
        public val separator: String = ".",
        public val prefix: () -> String = { "" },
        public val suffix: () -> String = { ": " },
        public val tags: List<String> = emptyList()
    ) : Logger {
        override fun pop(): Logger {
            return Default(separator, prefix, suffix, tags.dropLast(n = 1))
        }
        override fun get(tag: String): Default {
            return Default(separator, prefix, suffix, tags = tags + tag)
        }

        override fun log(vararg values: Any?, separator: String, end: String) {
            if (tags.isNotEmpty()) {
                print(prefix())
                print(tags.joinToString(this.separator))
                print(suffix())
            }
            print(values.joinToString(separator) + end)
        }
    }
}
