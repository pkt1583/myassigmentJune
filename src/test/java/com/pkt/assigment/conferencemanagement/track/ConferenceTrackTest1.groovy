package com.pkt.assigment.conferencemanagement.track

import com.pkt.assigment.conferencemanagement.session.ConferenceSession

class ConferenceTrackTest1 extends spock.lang.Specification {
    def sessionMap = ["WRITE_FAST_TESTS"    : ConferenceSession.builder().duration(Duration.ofMinutes(60L)).title("Writing Fast Tests Against Enterprise Rails").build(),
                      "OVERDOING_PYTHON"    : ConferenceSession.builder().duration(Duration.ofMinutes(45L)).title("Overdoing it in Python").build()
                      , "LUA_FOR_MASSES"    : ConferenceSession.builder().duration(Duration.ofMinutes(30L)).title("Lua for the Masses").build()
                      , "RUBY_ERRORS"       : ConferenceSession.builder().duration(Duration.ofMinutes(45L)).title("Ruby Errors from Mismatched Gem Versions").build()
                      , "COMMON_RUBY_ERRORS": ConferenceSession.builder().duration(Duration.ofMinutes(45L)).title("Common Ruby Errors").build()
                      , "RAILS_FOR_PYTHON"  : ConferenceSession.builder().duration(Duration.ofMinutes(5L)).title("Rails for Python Developers").build()
                      /*, "OVERDOING_PYTHON", ConferenceSession.builder().duration(Duration.ofMinutes(60L)).title("Communicating Over Distance").build()
                      , "OVERDOING_PYTHON", ConferenceSession.builder().duration(Duration.ofMinutes(45L)).title("Accounting-Driven Development").build()
                      , "OVERDOING_PYTHON", ConferenceSession.builder().duration(Duration.ofMinutes(30L)).title("Woah").build()
                      , "OVERDOING_PYTHON", ConferenceSession.builder().duration(Duration.ofMinutes(30L)).title("Sit Down and Write").build()
                      , "OVERDOING_PYTHON", ConferenceSession.builder().duration(Duration.ofMinutes(45L)).title("Pair Programming vs Noise").build()
                      , "OVERDOING_PYTHON", ConferenceSession.builder().duration(Duration.ofMinutes(60L)).title("Rails Magic").build()
                      , "OVERDOING_PYTHON", ConferenceSession.builder().duration(Duration.ofMinutes(60L)).title("Ruby on Rails: Why We Should Move On").build()
                      , "OVERDOING_PYTHON", ConferenceSession.builder().duration(Duration.ofMinutes(45L)).title("Clojure Ate Scala (on my project)").build()
                      , "OVERDOING_PYTHON", ConferenceSession.builder().duration(Duration.ofMinutes(30L)).title("Programming in the Boondocks of Seattle").build()
                      , "OVERDOING_PYTHON", ConferenceSession.builder().duration(Duration.ofMinutes(30L)).title("Ruby vs. Clojure for Back-End Development").build()
                      , "OVERDOING_PYTHON", ConferenceSession.builder().duration(Duration.ofMinutes(60L)).title("Ruby on Rails Legacy App Maintenance").build()*/
    ]


}
