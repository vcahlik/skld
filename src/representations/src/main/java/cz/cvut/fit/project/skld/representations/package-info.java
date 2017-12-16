/***
 * Contains classes representing data that's received and sent by the REST API.
 *
 * These classes are generally POJOs with some annotations used by Jackson (the de/serialization framework) to
 * convert them to JSON and Hibernate validation annotations which are then processed by Dropwizard, which automatically
 * refuses non-conforming requests.
 */
package cz.cvut.fit.project.skld.representations;