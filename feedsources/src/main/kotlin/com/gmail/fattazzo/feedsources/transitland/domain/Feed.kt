package com.gmail.fattazzo.feedsources.transitland.domain

import com.gmail.fattazzo.feedsources.Geometry
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

class Feed : Serializable {

    @SerializedName("geometry")
    var geometry: Geometry? = null

    @SerializedName("onestop_id")
    lateinit var onestopId: String

    @SerializedName("created_at")
    var createdAt: Date? = null

    @SerializedName("updated_at")
    var updatedAt: Date? = null

    @SerializedName("tags")
    var tag: Tag? = null

    @SerializedName("created_or_updated_in_changeset_id")
    var createdOrUpdatedInChangesetId: Int? = null

    @SerializedName("name")
    var name: String? = null

    @SerializedName("url")
    var url: String? = null

    @SerializedName("feed_format")
    var feedFormat: String? = null

    @SerializedName("license_name")
    var licenseName: String? = null

    @SerializedName("license_url")
    var licenseUrl: String? = null

    @SerializedName("license_use_without_attribution")
    var licenseUseWithoutAttribution: String? = null

    @SerializedName("license_create_derived_product")
    var licenseCreateDerivedProduct: String? = null

    @SerializedName("license_redistribute")
    var licenseRedistribute: String? = null

    @SerializedName("license_attribution_text")
    var licenseAttributionText: Any? = null

    @SerializedName("last_fetched_at")
    var lastFetchedAt: String? = null

    @SerializedName("last_imported_at")
    var lastImportedAt: String? = null

    @SerializedName("import_status")
    var importStatus: String? = null

    @SerializedName("feed_versions_count")
    var feedVersionsCount: Int? = null

    @SerializedName("feed_versions_url")
    var feedVersionsUrl: String? = null

    @SerializedName("feed_versions")
    var feedVersions: List<String>? = null

    @SerializedName("active_feed_version")
    var activeFeedVersion: String? = null

    @SerializedName("import_level_of_active_feed_version")
    var importLevelOfActiveFeedVersion: Int? = null

    @SerializedName("changesets_imported_from_this_feed")
    var changesetsImportedFromThisFeed: List<Int>? = null

    @SerializedName("operators_in_feed")
    var operatorsInFeed: List<OperatorsInFeed>? = null
}