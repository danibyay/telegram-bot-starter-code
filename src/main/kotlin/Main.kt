import dev.inmo.micro_utils.coroutines.subscribeSafelyWithoutExceptions
import dev.inmo.tgbotapi.extensions.api.chat.get.getChat
import dev.inmo.tgbotapi.extensions.api.send.*
import dev.inmo.tgbotapi.extensions.behaviour_builder.telegramBotWithBehaviourAndLongPolling
import dev.inmo.tgbotapi.extensions.behaviour_builder.triggers_handling.onContentMessage
import dev.inmo.tgbotapi.extensions.utils.extensions.raw.sender_chat
import dev.inmo.tgbotapi.extensions.utils.extensions.raw.text
import dev.inmo.tgbotapi.extensions.utils.formatting.linkMarkdownV2
import dev.inmo.tgbotapi.extensions.utils.formatting.textMentionMarkdownV2
import dev.inmo.tgbotapi.extensions.utils.ifChannelChat
import dev.inmo.tgbotapi.extensions.utils.ifFromChannelGroupContentMessage
import dev.inmo.tgbotapi.types.chat.*
import dev.inmo.tgbotapi.types.chat.GroupChat
import dev.inmo.tgbotapi.types.chat.PrivateChat
import dev.inmo.tgbotapi.types.chat.SupergroupChat
import dev.inmo.tgbotapi.types.message.MarkdownV2
import dev.inmo.tgbotapi.utils.PreviewFeature
import dev.inmo.tgbotapi.utils.extensions.escapeMarkdownV2Common
import kotlinx.coroutines.*

/**
 * The main purpose of this bot is just to answer "Oh, hi, " and add user mention here
 */
@OptIn(PreviewFeature::class)
suspend fun main() {
    //val botToken = args.first()

    telegramBotWithBehaviourAndLongPolling(System.getenv("TOKEN"), CoroutineScope(Dispatchers.IO)) {
        onContentMessage { message ->
            val chat = message.chat

            if (message.text?.contains("Arnold", ignoreCase = true) ?: false) {
                send(chat, "I am making things up again", MarkdownV2)
                return@onContentMessage
            }
            if (message.text?.contains("hello", ignoreCase = true) ?: false) {
                send(chat, "hello my name is elder cunningham", MarkdownV2)
                return@onContentMessage
            }
        }
        allUpdatesFlow.subscribeSafelyWithoutExceptions(this) { println(it) }
    }.second.join()
}
