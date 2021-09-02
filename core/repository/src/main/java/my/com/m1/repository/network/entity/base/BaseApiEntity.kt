package my.com.m1.repository.network.entity.base

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


open class BaseApiEntity : Serializable {

    @Expose
    @SerializedName("success")
    var isSuccess = false

    @Expose
    @SerializedName("targetUrl")
    var targetUrl: String? = null

    @Expose
    @SerializedName("error")
    var errorApi: BaseErrorApiEntity? = null

}