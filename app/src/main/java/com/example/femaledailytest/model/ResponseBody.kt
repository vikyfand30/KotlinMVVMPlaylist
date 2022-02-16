import com.google.gson.annotations.SerializedName

data class ResponseBody(
	@SerializedName("message") val message : Message
)