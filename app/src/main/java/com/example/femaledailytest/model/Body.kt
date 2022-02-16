import com.google.gson.annotations.SerializedName

data class Body(
    @SerializedName("track_list") val trackList: List<TrackList>
)