/**
 * Generated by apidoc - http://www.apidoc.me
 * Service version: 1.0.0
 * apidoc:0.9.13 http://www.apidoc.me/gilt/gilt-public-api/1.0.0/play_2_3_client
 */
package com.gilt.public.api.models {

case class Product(
                    name: String,
                    product: String,
                    id: Long,
                    brand: String,
                    url: String,
                    content: com.gilt.public.api.models.ProductContent,
                    categories: Seq[String],
                    imageUrls: _root_.play.api.libs.json.JsObject,
                    skus: Seq[com.gilt.public.api.models.Sku]
                    )

case class ProductCategories(
                              categories: Seq[String]
                              )

case class ProductContent(
                           description: _root_.scala.Option[String] = None,
                           fitNotes: _root_.scala.Option[String] = None,
                           material: _root_.scala.Option[String] = None,
                           careInstructions: _root_.scala.Option[String] = None,
                           origin: _root_.scala.Option[String] = None
                           )

case class SaleDetail(
                       name: String,
                       sale: String,
                       saleKey: String,
                       store: com.gilt.public.api.models.Store,
                       description: _root_.scala.Option[String] = None,
                       saleUrl: String,
                       begins: _root_.org.joda.time.DateTime,
                       ends: _root_.scala.Option[_root_.org.joda.time.DateTime] = None,
                       imageUrls: _root_.play.api.libs.json.JsObject,
                       products: _root_.scala.Option[Seq[String]] = None
                       )

case class SaleList(
                     sales: Seq[com.gilt.public.api.models.SaleDetail]
                     )

case class Sku(
                id: Long,
                inventoryStatus: String,
                msrpPrice: BigDecimal,
                salePrice: BigDecimal,
                shippingSurcharge: _root_.scala.Option[BigDecimal] = None,
                attributes: Seq[_root_.play.api.libs.json.JsObject]
                )

/**
 * Gilt.com is divided into stores. Currently there are four stores exposed by the
 * API: Women, Men, Baby & Kids and Home.
 */
sealed trait Store

object Store {

  case object Women extends Store { override def toString = "women" }
  case object Men extends Store { override def toString = "men" }
  case object Kids extends Store { override def toString = "kids" }
  case object Home extends Store { override def toString = "home" }

  /**
   * UNDEFINED captures values that are sent either in error or
   * that were added by the server after this library was
   * generated. We want to make it easy and obvious for users of
   * this library to handle this case gracefully.
   *
   * We use all CAPS for the variable name to avoid collisions
   * with the camel cased values above.
   */
  case class UNDEFINED(override val toString: String) extends Store

  /**
   * all returns a list of all the valid, known values. We use
   * lower case to avoid collisions with the camel cased values
   * above.
   */
  val all = Seq(Women, Men, Kids, Home)

  private[this]
  val byName = all.map(x => x.toString.toLowerCase -> x).toMap

  def apply(value: String): Store = fromString(value).getOrElse(UNDEFINED(value))

  def fromString(value: String): _root_.scala.Option[Store] = byName.get(value.toLowerCase)

}

}

package com.gilt.public.api.models {

package object json {
  import play.api.libs.json.__
  import play.api.libs.json.JsString
  import play.api.libs.json.Writes
  import play.api.libs.functional.syntax._
  import com.gilt.public.api.models.json._

  private[api] implicit val jsonReadsUUID = __.read[String].map(java.util.UUID.fromString)

  private[api] implicit val jsonWritesUUID = new Writes[java.util.UUID] {
    def writes(x: java.util.UUID) = JsString(x.toString)
  }

  private[api] implicit val jsonReadsJodaDateTime = __.read[String].map { str =>
    import org.joda.time.format.ISODateTimeFormat.dateTimeParser
    dateTimeParser.parseDateTime(str)
  }

  private[api] implicit val jsonWritesJodaDateTime = new Writes[org.joda.time.DateTime] {
    def writes(x: org.joda.time.DateTime) = {
      import org.joda.time.format.ISODateTimeFormat.dateTime
      val str = dateTime.print(x)
      JsString(str)
    }
  }

  implicit val jsonReadsGiltPublicApiStore = __.read[String].map(Store.apply)
  implicit val jsonWritesGiltPublicApiStore = new Writes[Store] {
    def writes(x: Store) = JsString(x.toString)
  }

  implicit def jsonReadsGiltPublicApiProduct: play.api.libs.json.Reads[Product] = {
    (
      (__ \ "name").read[String] and
        (__ \ "product").read[String] and
        (__ \ "id").read[Long] and
        (__ \ "brand").read[String] and
        (__ \ "url").read[String] and
        (__ \ "content").read[com.gilt.public.api.models.ProductContent] and
        (__ \ "categories").read[Seq[String]] and
        (__ \ "image_urls").read[_root_.play.api.libs.json.JsObject] and
        (__ \ "skus").read[Seq[com.gilt.public.api.models.Sku]]
      )(Product.apply _)
  }

  implicit def jsonWritesGiltPublicApiProduct: play.api.libs.json.Writes[Product] = {
    (
      (__ \ "name").write[String] and
        (__ \ "product").write[String] and
        (__ \ "id").write[Long] and
        (__ \ "brand").write[String] and
        (__ \ "url").write[String] and
        (__ \ "content").write[com.gilt.public.api.models.ProductContent] and
        (__ \ "categories").write[Seq[String]] and
        (__ \ "image_urls").write[_root_.play.api.libs.json.JsObject] and
        (__ \ "skus").write[Seq[com.gilt.public.api.models.Sku]]
      )(unlift(Product.unapply _))
  }

  implicit def jsonReadsGiltPublicApiProductCategories: play.api.libs.json.Reads[ProductCategories] = {
    (__ \ "categories").read[Seq[String]].map { x => new ProductCategories(categories = x) }
  }

  implicit def jsonWritesGiltPublicApiProductCategories: play.api.libs.json.Writes[ProductCategories] = new play.api.libs.json.Writes[ProductCategories] {
    def writes(x: ProductCategories) = play.api.libs.json.Json.obj(
      "categories" -> play.api.libs.json.Json.toJson(x.categories)
    )
  }

  implicit def jsonReadsGiltPublicApiProductContent: play.api.libs.json.Reads[ProductContent] = {
    (
      (__ \ "description").readNullable[String] and
        (__ \ "fit_notes").readNullable[String] and
        (__ \ "material").readNullable[String] and
        (__ \ "care_instructions").readNullable[String] and
        (__ \ "origin").readNullable[String]
      )(ProductContent.apply _)
  }

  implicit def jsonWritesGiltPublicApiProductContent: play.api.libs.json.Writes[ProductContent] = {
    (
      (__ \ "description").writeNullable[String] and
        (__ \ "fit_notes").writeNullable[String] and
        (__ \ "material").writeNullable[String] and
        (__ \ "care_instructions").writeNullable[String] and
        (__ \ "origin").writeNullable[String]
      )(unlift(ProductContent.unapply _))
  }

  implicit def jsonReadsGiltPublicApiSaleDetail: play.api.libs.json.Reads[SaleDetail] = {
    (
      (__ \ "name").read[String] and
        (__ \ "sale").read[String] and
        (__ \ "sale_key").read[String] and
        (__ \ "store").read[com.gilt.public.api.models.Store] and
        (__ \ "description").readNullable[String] and
        (__ \ "sale_url").read[String] and
        (__ \ "begins").read[_root_.org.joda.time.DateTime] and
        (__ \ "ends").readNullable[_root_.org.joda.time.DateTime] and
        (__ \ "image_urls").read[_root_.play.api.libs.json.JsObject] and
        (__ \ "products").readNullable[Seq[String]]
      )(SaleDetail.apply _)
  }

  implicit def jsonWritesGiltPublicApiSaleDetail: play.api.libs.json.Writes[SaleDetail] = {
    (
      (__ \ "name").write[String] and
        (__ \ "sale").write[String] and
        (__ \ "sale_key").write[String] and
        (__ \ "store").write[com.gilt.public.api.models.Store] and
        (__ \ "description").writeNullable[String] and
        (__ \ "sale_url").write[String] and
        (__ \ "begins").write[_root_.org.joda.time.DateTime] and
        (__ \ "ends").writeNullable[_root_.org.joda.time.DateTime] and
        (__ \ "image_urls").write[_root_.play.api.libs.json.JsObject] and
        (__ \ "products").writeNullable[Seq[String]]
      )(unlift(SaleDetail.unapply _))
  }

  implicit def jsonReadsGiltPublicApiSaleList: play.api.libs.json.Reads[SaleList] = {
    (__ \ "sales").read[Seq[com.gilt.public.api.models.SaleDetail]].map { x => new SaleList(sales = x) }
  }

  implicit def jsonWritesGiltPublicApiSaleList: play.api.libs.json.Writes[SaleList] = new play.api.libs.json.Writes[SaleList] {
    def writes(x: SaleList) = play.api.libs.json.Json.obj(
      "sales" -> play.api.libs.json.Json.toJson(x.sales)
    )
  }

  implicit def jsonReadsGiltPublicApiSku: play.api.libs.json.Reads[Sku] = {
    (
      (__ \ "id").read[Long] and
        (__ \ "inventory_status").read[String] and
        (__ \ "msrp_price").read[BigDecimal] and
        (__ \ "sale_price").read[BigDecimal] and
        (__ \ "shipping_surcharge").readNullable[BigDecimal] and
        (__ \ "attributes").read[Seq[_root_.play.api.libs.json.JsObject]]
      )(Sku.apply _)
  }

  implicit def jsonWritesGiltPublicApiSku: play.api.libs.json.Writes[Sku] = {
    (
      (__ \ "id").write[Long] and
        (__ \ "inventory_status").write[String] and
        (__ \ "msrp_price").write[BigDecimal] and
        (__ \ "sale_price").write[BigDecimal] and
        (__ \ "shipping_surcharge").writeNullable[BigDecimal] and
        (__ \ "attributes").write[Seq[_root_.play.api.libs.json.JsObject]]
      )(unlift(Sku.unapply _))
  }
}
}

package com.gilt.public.api {

object Bindables {

  import play.api.mvc.{PathBindable, QueryStringBindable}
  import org.joda.time.{DateTime, LocalDate}
  import org.joda.time.format.ISODateTimeFormat
  import com.gilt.public.api.models._

  // Type: date-time-iso8601
  implicit val pathBindableTypeDateTimeIso8601 = new PathBindable.Parsing[org.joda.time.DateTime](
    ISODateTimeFormat.dateTimeParser.parseDateTime(_), _.toString, (key: String, e: Exception) => s"Error parsing date time $key. Example: 2014-04-29T11:56:52Z"
  )

  implicit val queryStringBindableTypeDateTimeIso8601 = new QueryStringBindable.Parsing[org.joda.time.DateTime](
    ISODateTimeFormat.dateTimeParser.parseDateTime(_), _.toString, (key: String, e: Exception) => s"Error parsing date time $key. Example: 2014-04-29T11:56:52Z"
  )

  // Type: date-iso8601
  implicit val pathBindableTypeDateIso8601 = new PathBindable.Parsing[org.joda.time.LocalDate](
    ISODateTimeFormat.yearMonthDay.parseLocalDate(_), _.toString, (key: String, e: Exception) => s"Error parsing date $key. Example: 2014-04-29"
  )

  implicit val queryStringBindableTypeDateIso8601 = new QueryStringBindable.Parsing[org.joda.time.LocalDate](
    ISODateTimeFormat.yearMonthDay.parseLocalDate(_), _.toString, (key: String, e: Exception) => s"Error parsing date $key. Example: 2014-04-29"
  )

  // Enum: Store
  private val enumStoreNotFound = (key: String, e: Exception) => s"Unrecognized $key, should be one of ${com.gilt.public.api.models.Store.all.mkString(", ")}"

  implicit val pathBindableEnumStore = new PathBindable.Parsing[com.gilt.public.api.models.Store] (
    Store.fromString(_).get, _.toString, enumStoreNotFound
  )

  implicit val queryStringBindableEnumStore = new QueryStringBindable.Parsing[com.gilt.public.api.models.Store](
    Store.fromString(_).get, _.toString, enumStoreNotFound
  )

}

}


package com.gilt.public.api {

object Constants {

  val UserAgent = "apidoc:0.9.13 http://www.apidoc.me/gilt/gilt-public-api/1.0.0/play_2_3_client"
  val Version = "1.0.0"
  val VersionMajor = 1

}

class Client(
              apiUrl: String,
              auth: scala.Option[com.gilt.public.api.Authorization] = None,
              defaultHeaders: Seq[(String, String)] = Nil
              ) {
  import com.gilt.public.api.models.json._

  private val logger = play.api.Logger("com.gilt.public.api.Client")

  logger.info(s"Initializing com.gilt.public.api.Client for url $apiUrl")

  def products: Products = Products

  def saleDetails: SaleDetails = SaleDetails

  def saleList: SaleList = SaleList

  object Products extends Products {
    override def getDetailJsonByProductId(
                                           productId: String,
                                           apikey: String
                                           )(implicit ec: scala.concurrent.ExecutionContext): scala.concurrent.Future[com.gilt.public.api.models.Product] = {
      val queryParameters = Seq(
        Some("apikey" -> apikey)
      ).flatten

      _executeRequest("GET", s"/products/${play.utils.UriEncoding.encodePathSegment(productId, "UTF-8")}/detail.json", queryParameters = queryParameters).map {
        case r if r.status == 200 => _root_.com.gilt.public.api.Client.parseJson("com.gilt.public.api.models.Product", r, _.validate[com.gilt.public.api.models.Product])
        case r => throw new com.gilt.public.api.errors.FailedRequest(r.status, s"Unsupported response code[${r.status}]. Expected: 200")
      }
    }

    override def getCategoriesJson(
                                    apikey: String
                                    )(implicit ec: scala.concurrent.ExecutionContext): scala.concurrent.Future[com.gilt.public.api.models.ProductCategories] = {
      val queryParameters = Seq(
        Some("apikey" -> apikey)
      ).flatten

      _executeRequest("GET", s"/products/categories.json", queryParameters = queryParameters).map {
        case r if r.status == 200 => _root_.com.gilt.public.api.Client.parseJson("com.gilt.public.api.models.ProductCategories", r, _.validate[com.gilt.public.api.models.ProductCategories])
        case r => throw new com.gilt.public.api.errors.FailedRequest(r.status, s"Unsupported response code[${r.status}]. Expected: 200")
      }
    }
  }

  object SaleDetails extends SaleDetails {
    override def getSalesByStoreAndSaledetailJson(
                                                   store: com.gilt.public.api.models.Store,
                                                   saledetailJson: String,
                                                   apikey: String
                                                   )(implicit ec: scala.concurrent.ExecutionContext): scala.concurrent.Future[com.gilt.public.api.models.SaleDetail] = {
      val queryParameters = Seq(
        Some("apikey" -> apikey)
      ).flatten

      _executeRequest("GET", s"/sales/${play.utils.UriEncoding.encodePathSegment(store.toString, "UTF-8")}/${play.utils.UriEncoding.encodePathSegment(saledetailJson, "UTF-8")}", queryParameters = queryParameters).map {
        case r if r.status == 200 => _root_.com.gilt.public.api.Client.parseJson("com.gilt.public.api.models.SaleDetail", r, _.validate[com.gilt.public.api.models.SaleDetail])
        case r => throw new com.gilt.public.api.errors.FailedRequest(r.status, s"Unsupported response code[${r.status}]. Expected: 200")
      }
    }
  }

  object SaleList extends SaleList {
    override def getSalesAndActiveJson(
                                        apikey: String
                                        )(implicit ec: scala.concurrent.ExecutionContext): scala.concurrent.Future[com.gilt.public.api.models.SaleList] = {
      val queryParameters = Seq(
        Some("apikey" -> apikey)
      ).flatten

      _executeRequest("GET", s"/sales/active.json", queryParameters = queryParameters).map {
        case r if r.status == 200 => _root_.com.gilt.public.api.Client.parseJson("com.gilt.public.api.models.SaleList", r, _.validate[com.gilt.public.api.models.SaleList])
        case r => throw new com.gilt.public.api.errors.FailedRequest(r.status, s"Unsupported response code[${r.status}]. Expected: 200")
      }
    }

    override def getSalesAndActiveJsonByStore(
                                               store: String,
                                               apikey: String
                                               )(implicit ec: scala.concurrent.ExecutionContext): scala.concurrent.Future[com.gilt.public.api.models.SaleList] = {
      val queryParameters = Seq(
        Some("apikey" -> apikey)
      ).flatten

      _executeRequest("GET", s"/sales/${play.utils.UriEncoding.encodePathSegment(store, "UTF-8")}/active.json", queryParameters = queryParameters).map {
        case r if r.status == 200 => _root_.com.gilt.public.api.Client.parseJson("com.gilt.public.api.models.SaleList", r, _.validate[com.gilt.public.api.models.SaleList])
        case r => throw new com.gilt.public.api.errors.FailedRequest(r.status, s"Unsupported response code[${r.status}]. Expected: 200")
      }
    }

    override def getSalesAndUpcomingJson(
                                          apikey: String
                                          )(implicit ec: scala.concurrent.ExecutionContext): scala.concurrent.Future[com.gilt.public.api.models.SaleList] = {
      val queryParameters = Seq(
        Some("apikey" -> apikey)
      ).flatten

      _executeRequest("GET", s"/sales/upcoming.json", queryParameters = queryParameters).map {
        case r if r.status == 200 => _root_.com.gilt.public.api.Client.parseJson("com.gilt.public.api.models.SaleList", r, _.validate[com.gilt.public.api.models.SaleList])
        case r => throw new com.gilt.public.api.errors.FailedRequest(r.status, s"Unsupported response code[${r.status}]. Expected: 200")
      }
    }

    override def getSalesAndUpcomingJsonByStore(
                                                 store: String,
                                                 apikey: String
                                                 )(implicit ec: scala.concurrent.ExecutionContext): scala.concurrent.Future[com.gilt.public.api.models.SaleList] = {
      val queryParameters = Seq(
        Some("apikey" -> apikey)
      ).flatten

      _executeRequest("GET", s"/sales/${play.utils.UriEncoding.encodePathSegment(store, "UTF-8")}/upcoming.json", queryParameters = queryParameters).map {
        case r if r.status == 200 => _root_.com.gilt.public.api.Client.parseJson("com.gilt.public.api.models.SaleList", r, _.validate[com.gilt.public.api.models.SaleList])
        case r => throw new com.gilt.public.api.errors.FailedRequest(r.status, s"Unsupported response code[${r.status}]. Expected: 200")
      }
    }
  }

  def _requestHolder(path: String): play.api.libs.ws.WSRequestHolder = {
    import play.api.Play.current

    val holder = play.api.libs.ws.WS.url(apiUrl + path).withHeaders(
      "User-Agent" -> Constants.UserAgent,
      "X-Apidoc-Version" -> Constants.Version,
      "X-Apidoc-Version-Major" -> Constants.VersionMajor.toString
    ).withHeaders(defaultHeaders : _*)
    auth.fold(holder) { a =>
      a match {
        case Authorization.Basic(username, password) => {
          holder.withAuth(username, password.getOrElse(""), play.api.libs.ws.WSAuthScheme.BASIC)
        }
        case _ => sys.error("Invalid authorization scheme[" + a.getClass + "]")
      }
    }
  }

  def _logRequest(method: String, req: play.api.libs.ws.WSRequestHolder)(implicit ec: scala.concurrent.ExecutionContext): play.api.libs.ws.WSRequestHolder = {
    val queryComponents = for {
      (name, values) <- req.queryString
      value <- values
    } yield name -> value
    val url = s"${req.url}${queryComponents.mkString("?", "&", "")}"
    auth.fold(logger.info(s"curl -X $method $url")) { _ =>
      logger.info(s"curl -X $method -u '[REDACTED]:' $url")
    }
    req
  }

  def _executeRequest(
                       method: String,
                       path: String,
                       queryParameters: Seq[(String, String)] = Seq.empty,
                       body: Option[play.api.libs.json.JsValue] = None
                       )(implicit ec: scala.concurrent.ExecutionContext): scala.concurrent.Future[play.api.libs.ws.WSResponse] = {
    println(path)
    method.toUpperCase match {
      case "GET" => {
        _logRequest("GET", _requestHolder(path).withQueryString(queryParameters:_*)).get()
      }
      case "POST" => {
        _logRequest("POST", _requestHolder(path).withQueryString(queryParameters:_*)).post(body.getOrElse(play.api.libs.json.Json.obj()))
      }
      case "PUT" => {
        _logRequest("PUT", _requestHolder(path).withQueryString(queryParameters:_*)).put(body.getOrElse(play.api.libs.json.Json.obj()))
      }
      case "PATCH" => {
        _logRequest("PATCH", _requestHolder(path).withQueryString(queryParameters:_*)).patch(body.getOrElse(play.api.libs.json.Json.obj()))
      }
      case "DELETE" => {
        _logRequest("DELETE", _requestHolder(path).withQueryString(queryParameters:_*)).delete()
      }
      case "HEAD" => {
        _logRequest("HEAD", _requestHolder(path).withQueryString(queryParameters:_*)).head()
      }
      case "OPTIONS" => {
        _logRequest("OPTIONS", _requestHolder(path).withQueryString(queryParameters:_*)).options()
      }
      case _ => {
        _logRequest(method, _requestHolder(path).withQueryString(queryParameters:_*))
        sys.error("Unsupported method[%s]".format(method))
      }
    }
  }

}

object Client {

  def parseJson[T](
                    className: String,
                    r: play.api.libs.ws.WSResponse,
                    f: (play.api.libs.json.JsValue => play.api.libs.json.JsResult[T])
                    ): T = {
    f(play.api.libs.json.Json.parse(r.body)) match {
      case play.api.libs.json.JsSuccess(x, _) => x
      case play.api.libs.json.JsError(errors) => {
        throw new com.gilt.public.api.errors.FailedRequest(r.status, s"Invalid json for class[" + className + "]: " + errors.mkString(" "))
      }
    }
  }

}

sealed trait Authorization
object Authorization {
  case class Basic(username: String, password: Option[String] = None) extends Authorization
}

trait Products {
  def getDetailJsonByProductId(
                                productId: String,
                                apikey: String
                                )(implicit ec: scala.concurrent.ExecutionContext): scala.concurrent.Future[com.gilt.public.api.models.Product]

  def getCategoriesJson(
                         apikey: String
                         )(implicit ec: scala.concurrent.ExecutionContext): scala.concurrent.Future[com.gilt.public.api.models.ProductCategories]
}

trait SaleDetails {
  def getSalesByStoreAndSaledetailJson(
                                        store: com.gilt.public.api.models.Store,
                                        saledetailJson: String,
                                        apikey: String
                                        )(implicit ec: scala.concurrent.ExecutionContext): scala.concurrent.Future[com.gilt.public.api.models.SaleDetail]
}

trait SaleList {
  def getSalesAndActiveJson(
                             apikey: String
                             )(implicit ec: scala.concurrent.ExecutionContext): scala.concurrent.Future[com.gilt.public.api.models.SaleList]

  def getSalesAndActiveJsonByStore(
                                    store: String,
                                    apikey: String
                                    )(implicit ec: scala.concurrent.ExecutionContext): scala.concurrent.Future[com.gilt.public.api.models.SaleList]

  def getSalesAndUpcomingJson(
                               apikey: String
                               )(implicit ec: scala.concurrent.ExecutionContext): scala.concurrent.Future[com.gilt.public.api.models.SaleList]

  def getSalesAndUpcomingJsonByStore(
                                      store: String,
                                      apikey: String
                                      )(implicit ec: scala.concurrent.ExecutionContext): scala.concurrent.Future[com.gilt.public.api.models.SaleList]
}

package errors {

case class FailedRequest(responseCode: Int, message: String, requestUri: Option[_root_.java.net.URI] = None) extends Exception(s"HTTP $responseCode: $message")

}

}