package com.gmail.fattazzo.publictransportgtfs.feeds.source.transitland.domain

import com.gmail.fattazzo.publictransportgtfs.feeds.source.Geometry
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Operator: Serializable {

    @SerializedName("geometry")
    var geometry: Geometry? = null

    @SerializedName("onestop_id")
    var onestopId: String? = null

    @SerializedName("created_at")
    var createdAt: String? = null

    @SerializedName("updated_at")
    var updatedAt: String? = null

    @SerializedName("tags")
    var tag: Tag? = null

    @SerializedName("created_or_updated_in_changeset_id")
    var createdOrUpdatedInChangesetId: Int? = null

    @SerializedName("name")
    var name: String? = null

    @SerializedName("short_name")
    var shortName: String? = null

    @SerializedName("website")
    var website: String? = null

    @SerializedName("country")
    var country: String? = null

    @SerializedName("state")
    var state: String? = null

    @SerializedName("metro")
    var metro: String? = null

    @SerializedName("timezone")
    var timezone: String? = null

    @SerializedName("represented_in_feed_onestop_ids")
    var representedInFeedOnestopIds: List<String>? = null
}