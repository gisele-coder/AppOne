import android.content.Context
import android.util.Log
import com.example.giselecoder.Post
import com.example.giselecoder.R

object PostUtils {
    private const val TAG = "PostUtils"

    fun getPostsFromStrings(context: Context): List<Post> {
        val posts = mutableListOf<Post>()
        try {
            val resources = context.resources
            val postArray = resources.getStringArray(R.array.posts)

            for (postString in postArray) {
                val postParts = postString.split(";")
                if (postParts.size == 3) {
                    val post = Post(postParts[0], postParts[1], postParts[2])
                    posts.add(post)
                } else {
                    Log.e(TAG, "Formato de post inválido: $postString")
                }
            }
        } catch (e: Exception) {
            Log.e(TAG, "Erro ao obter posts: ${e.message}")
        }
        return posts
    }
}
