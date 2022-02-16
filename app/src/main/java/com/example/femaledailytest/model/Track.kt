import com.example.femaledailytest.model.PrimaryGenres
import com.google.gson.annotations.SerializedName

data class Track(
    @SerializedName("track_id") val trackId: Int,
    @SerializedName("track_name") val trackName: String,
    @SerializedName("track_name_translation_list") val trackNameTranslationList: List<String>,
    @SerializedName("track_rating") val trackRating: Int,
    @SerializedName("commontrack_id") val commonTrackId: Int,
    @SerializedName("instrumental") val instrumental: Int,
    @SerializedName("explicit") val explicit: Int,
    @SerializedName("has_lyrics") val hasLyric: Int,
    @SerializedName("has_subtitles") val hasSubtitle: Int,
    @SerializedName("has_richsync") val hasRichSync: Int,
    @SerializedName("num_favourite") val numFavourite: Int,
    @SerializedName("album_id") val albumId: Int,
    @SerializedName("album_name") val albumName: String,
    @SerializedName("artist_id") val artistId: Int,
    @SerializedName("artist_name") val artistName: String,
    @SerializedName("track_share_url") val trackShareUrl: String,
    @SerializedName("track_edit_url") val trackEditUrl: String,
    @SerializedName("restricted") val restricted: Int,
    @SerializedName("updated_time") val updatedTime: String,
    @SerializedName("primary_genres") val primaryGenres: PrimaryGenres
)