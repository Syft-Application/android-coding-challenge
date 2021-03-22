package com.syftapp.codetest.data.api

import com.syftapp.codetest.data.model.domain.Comment
import com.syftapp.codetest.data.model.domain.Post
import com.syftapp.codetest.data.model.domain.User
import io.mockk.MockKAnnotations
import io.reactivex.Single
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

class BlogApiTest {

    private lateinit var blogService: StubBlogService
    private lateinit var sut: BlogApi

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        blogService = StubBlogService()
        sut = BlogApi(blogService)
    }

    @Test
    fun `get users contains correct domain models`() {
        val apiUser = rxValue(blogService.getUsers()).get(0)
        val users = rxValue(sut.getUsers())

        assertThat(users)
            .hasSize(2)
            .contains(
                User(
                    id = apiUser.id,
                    name = apiUser.name,
                    username = apiUser.username,
                    email = apiUser.email
                )
            )
    }

    @Test
    fun `get posts contains correct domain models`() {
        val apiPost = rxValue(blogService.getPosts(1)).get(0)
        val posts = rxValue(sut.getPosts(1))

        assertThat(posts)
            .hasSize(5)
            .contains(
                Post(
                    id = apiPost.id,
                    userId = apiPost.userId,
                    title = apiPost.title,
                    body = apiPost.body
                )
            )
    }

    @Test
    fun `get comments contains correct domain models`() {
        val apiComment = rxValue(blogService.getComments()).get(0)
        val comments = rxValue(sut.getComments())

        assertThat(comments)
            .hasSize(3)
            .contains(
                Comment(
                    id = apiComment.id,
                    postId = apiComment.postId,
                    name = apiComment.name,
                    email = apiComment.email,
                    body = apiComment.body
                )
            )
    }

    private fun <T> rxValue(apiItem: Single<T>): T = apiItem.test().values().get(0)
}