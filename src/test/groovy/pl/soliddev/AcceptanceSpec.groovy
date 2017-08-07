package pl.soliddev

import pl.soliddev.base.IntegrationSpec

class AcceptanceSpec extends IntegrationSpec {

    def "positive flatagent scenario"() {
        given: 'external service has in offer a flat 50m2 on Kosciuszki 2 Street on 2nd floor and 80m2 flat on Strzegomska 50 Street on 5th floor'

        when: 'I go to /flats'
        then: 'I see no flats'

        when: 'I go to /update'
        then: 'I see that two flats are added'

        when: 'I go to /flats'
        then: 'I see both flats'

        when: 'I go to /mycriteria'
        then: 'I see I have no criteria'

        when: 'I go to /flats/withMyCriteria'
        then: 'I see both flats'

        when: 'I post to /mycriteria with flatFieldMin equal 60m2'
        then: 'I see I have criterium flatFieldMin equal 60m2'

        when: 'I go to /flats/withMyCriteria'
        then: 'I see 80m2 flat on Strzegomska 50 Street on 5th floor'

        when: 'I post to /mycriteria with street equal Kosciuszki'
        then: 'I see I have criterium flatFieldMin equal 60m2 and street equal Kosciuszki'

        when: 'I go to /flats/withMyCriteria'
        then: 'I see no flats'

        when: 'I post to /mycriteria with street equal Strzegomska'
        then: 'I see I have criterium flatFieldMin equal 60m2 and street equal [Kosciuszki, Strzegomska]'

        when: 'I go to /flats/withMyCriteria'
        then: 'I see 80m2 flat on Strzegomska 50 Street on 5th floor'

    }
}
