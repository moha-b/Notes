package model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "Notes")
data class Notes(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var title: String,
    var content: String,
    var color: Int,
    var image: String,
    var love: Int,
): Parcelable
