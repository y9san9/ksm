package me.y9san9.pipeline.docs

import me.y9san9.pipeline.context.PipelineContext
import me.y9san9.pipeline.context.require

public interface PipelineDocs {
    public val context: PipelineContext

    public companion object {
        public fun require(context: PipelineContext) {
            context.require(DocsName)
        }
        public fun of(context: PipelineContext = PipelineContext.Empty): PipelineDocs {
            require(context)
            return object : PipelineDocs {
                override val context = context
                override fun toString() = "PipelineDocs(context=$context)"
            }
        }
    }
}

public fun PipelineDocs.prettyString(): String {
    return buildString {
        val phases = phases

        if (phases != null) {
            appendLine("Pipeline '$name':")
            appendLine("> $description")
            if (contextDescription != null) {
                append("Context: ")
                appendListDocs(contextDescription)
                appendLine()
            }
            if (subjectDescription != null) {
                append("Subject: ")
                appendListDocs(subjectDescription)
                appendLine()
            }

            append("┌───────── PHASES ──────────")

            for ((i, phase) in phases.withIndex()) {
                appendLine()

                val number = "${i + 1}. "
                val tabSize = 6
                val tabDelta = (tabSize - number.length).coerceAtLeast(0)
                val tab = buildString {
                    append("│")
                    repeat(tabSize - 1) { append(' ') }
                }

                append("│ ")
                repeat(tabDelta - 2) { append(' ') }
                append(number)

                val string = phase
                    .prettyString()
                    .prependIndent(tab)
                    .drop(tabSize)

                append(string)
            }
            appendLine()
            append("└───────────────────────────")
        } else {
            append("Phase '$name':")
            val description = description
            if (description != null) {
                appendLine()
                append(description.prependIndent("> "))
            }
            if (contextDescription != null) {
                appendLine()
                append("Context: ")
                appendListDocs(contextDescription)
            }
            if (subjectDescription != null) {
                appendLine()
                append("Subject: ")
                appendListDocs(subjectDescription)
            }
        }
    }
}

private fun StringBuilder.appendListDocs(list: List<String>?) {
    list ?: return
    if (list.isEmpty()) return

    appendLine()
    append(list.joinToString(separator = "\n").prependIndent(indent = "  • "))
}
