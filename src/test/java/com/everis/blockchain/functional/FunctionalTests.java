package com.everis.blockchain.functional;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/*
 * INT: mvn -Dtest=com.everis.blockchain.functional.FunctionalTests test
 * INT: mvn -Dtest=com.everis.blockchain.functional.FunctionalTests test -Dcountry=DE -Dlanguage=DE
 * CNS: mvn -Dtest=com.everis.blockchain.functional.FunctionalTests test -Dserver.port=443 -Dserver.host=https://www.dbe-con.seat.es/
 * CNS: mvn -Dtest=com.everis.blockchain.functional.FunctionalTests test -Dserver.port=443 -Dserver.host=https://www.dbe-con.seat.es/
*/
@RunWith(Suite.class)
@SuiteClasses(value = {FunctionalBase.class})
public class FunctionalTests {
}
