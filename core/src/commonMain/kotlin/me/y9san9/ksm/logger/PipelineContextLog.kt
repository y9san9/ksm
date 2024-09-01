package me.y9san9.ksm.logger

import me.y9san9.pipeline.builder.PipelineBuilder
import me.y9san9.pipeline.builder.with
import me.y9san9.pipeline.context.PipelineContext
import me.y9san9.pipeline.context.with
import me.y9san9.pipeline.context.withDecorator

public val PipelineContext.logger: Logger?
    get() = this[LoggerPlugin.Logger]

public fun PipelineContext.log(
    vararg values: Any?,
    separator: String = " ",
    end: String = "\n"
) {
    val logger = this[LoggerPlugin.Logger] ?: return
    logger.log(*values, separator = separator, end = end)
}

public fun PipelineContext.withLogger(
    logger: Logger? = Logger
): PipelineContext {
    return with(LoggerPlugin.Logger, logger)
}

public fun PipelineBuilder.withLogger(
    logger: Logger? = Logger
) {
    with(LoggerPlugin.Logger, logger)
}

public fun PipelineContext.withTag(tag: String): PipelineContext {
    return withDecorator(LoggerPlugin.Logger) { base ->
        base?.get(tag)
    }
}

public fun PipelineContext.popLogger(): PipelineContext {
    return withDecorator(LoggerPlugin.Logger) { base ->
        base?.pop()
    }
}
