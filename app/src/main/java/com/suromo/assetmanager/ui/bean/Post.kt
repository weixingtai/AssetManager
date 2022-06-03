package com.suromo.assetmanager.ui.bean

import androidx.annotation.DrawableRes

/**
 * author : Samuel
 * e-mail : xingtai.wei@icloud.com
 * time   : 2022/06/2022/6/1
 * desc   :
 */
data class Post(
    val id: String,
    val price: String,
    val title: String,
    val description: String,
    val type: String,
    @DrawableRes val imageId: Int
)

data class Metadata(
    val author: PostAuthor,
    val date: String,
    val readTimeMinutes: Int
)

data class PostAuthor(
    val name: String,
    val url: String? = null
)

data class Publication(
    val name: String,
    val logoUrl: String
)

data class Paragraph(
    val type: ParagraphType,
    val text: String,
    val markups: List<Markup> = emptyList()
)

data class Markup(
    val type: MarkupType,
    val start: Int,
    val end: Int,
    val href: String? = null
)

enum class MarkupType {
    Link,
    Code,
    Italic,
    Bold,
}

enum class ParagraphType {
    Title,
    Caption,
    Header,
    Subhead,
    Text,
    CodeBlock,
    Quote,
    Bullet,
}
