import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.junit.After as After
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory as CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as MobileBuiltInKeywords
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testcase.TestCaseFactory as TestCaseFactory
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository as ObjectRepository
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WSBuiltInKeywords
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUiBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

def config = TestDataFactory.findTestData('Configuration')

WebUI.openBrowser(config.getValue('url', 1))

//********************************************************Login as an admin********************************************************//

WebUI.setText(findTestObject('Monitoring/Downtimes/input_useralias'), config.getValue('login', 1))

WebUI.setText(findTestObject('Monitoring/Downtimes/input_password'), config.getValue('password', 1))

WebUI.click(findTestObject('General/Login/input_submitLogin'))

//**************************************************Go to Status details services**************************************************//

CustomKeywords.'custom.NavigationMonitoring.accessStatusDetailsServices'()

WebUI.waitForPageLoad(3)

//******************************************************Configure the service******************************************************//

//This put the service status to 'All'
WebUI.selectOptionByValue(findTestObject('Monitoring/Status details/Services/select_Unhandled Problems'), 'svc', true)

//The following lines access to the targeted service's page
def servicesFile = TestDataFactory.findTestData('Services')

def hostFile = TestDataFactory.findTestData('Host data')

def search = WebUI.modifyObjectProperty(findTestObject('General/input_Search'), 'name', 'equals', 'host_search', true)

WebUI.setText(search, config.getValue('TimeIndicator', 1) + hostFile.getValue('hostName', 1) + '1')

search = WebUI.modifyObjectProperty(search, 'name', 'equals', 'search', true)

WebUI.setText(search, servicesFile.getValue(1, 4))

WebUI.delay(1)

def element = WebUI.modifyObjectProperty(findTestObject('Monitoring/Status details/Services/a_Service name'), 'text', 'equals', 
    servicesFile.getValue(1, 4), true)

WebUI.click(element)

WebUI.waitForPageLoad(3)

//Now we disable active checks and enable passive ones to be able to modify easily the status of a service
if(WebUI.verifyElementNotPresent(findTestObject('Monitoring/Status details/Services/a_Submit results'), 1, FailureHandling.OPTIONAL)){
	WebUI.click(findTestObject('Monitoring/Status details/Services/img_Active checks'))
	
	WebUI.waitForAlert(3)
	
	WebUI.acceptAlert()
	
	WebUI.delay(1)
	
	WebUI.click(findTestObject('Monitoring/Status details/Services/img_Passive checks'))
	
	WebUI.waitForAlert(3)
	
	WebUI.acceptAlert()
	
	WebUI.delay(2)
	
	WebUI.refresh()
}

WebUI.click(findTestObject('Monitoring/Status details/Services/a_Submit results'))

WebUI.selectOptionByLabel(findTestObject('Monitoring/Status details/Services/select_Check result'), 'OK', true)

WebUI.click(findTestObject('Monitoring/Status details/Services/save result'))

//*********************************************************Go to Downtimes*********************************************************//

CustomKeywords.'custom.NavigationMonitoring.accessDowntimes'()

WebUI.waitForPageLoad(3)

//*****************************************************Delete all the downtimes****************************************************//

WebUI.delay(1)

if (WebUI.verifyElementPresent(findTestObject('Monitoring/Downtimes/input_Cancel button'), 1, FailureHandling.OPTIONAL)) {
	
    WebUI.click(findTestObject('General/input_Checkall'))

    WebUI.click(findTestObject('Monitoring/Downtimes/input_Cancel button'), FailureHandling.OPTIONAL)
	
	WebUI.waitForAlert(3)

    WebUI.acceptAlert()
	
	WebUI.delay(1)
}

//********************************************************Create a downtime********************************************************//

WebUI.click(findTestObject('Monitoring/Downtimes/a_Add a downtime'))

WebUI.waitForElementClickable(findTestObject('Monitoring/Downtimes/input_downtimeType'), 3)

WebUI.click(findTestObject('Monitoring/Downtimes/input_downtimeType'))

element = WebUI.modifyObjectProperty(findTestObject('Monitoring/Downtimes/input_Services'), 'placeholder', 'equals', 'Services', 
    true)

WebUI.waitForElementClickable(element, 3)

WebUI.click(element)

element = WebUI.modifyObjectProperty(findTestObject('Monitoring/Downtimes/li_pouic - Ping'), 'text', 'equals', 
	(config.getValue('TimeIndicator', 1) + hostFile.getValue('hostName', 1) + '1 - ') + servicesFile.getValue(1, 4), true)

WebUI.click(element)

WebUI.click(findTestObject('Monitoring/Downtimes/input_Fixed'))

WebUI.setText(findTestObject('Monitoring/Downtimes/input_Duration'), '500')

WebUI.setText(findTestObject('Monitoring/Downtimes/textarea_comment'), 'Katalon comment')

WebUI.click(findTestObject('General/input_submitA'))

//Wait to be sure Edge is not too fast
WebUI.waitForPageLoad(3)

//************************************************Go to Status Details services page***********************************************//

CustomKeywords.'custom.NavigationMonitoring.accessStatusDetailsServices'()

WebUI.waitForPageLoad(3)

//******************************************************Configure the service******************************************************//

//This put the service status to 'All'
WebUI.selectOptionByValue(findTestObject('Monitoring/Status details/Services/select_Unhandled Problems'), 'svc', true)

//The following lines access to the targeted service's page
servicesFile = TestDataFactory.findTestData('Services')

hostFile = TestDataFactory.findTestData('Host data')

search = WebUI.modifyObjectProperty(findTestObject('General/input_Search'), 'name', 'equals', 'host_search', true)

WebUI.setText(search, config.getValue('TimeIndicator', 1) + hostFile.getValue('hostName', 1) + '1')

search = WebUI.modifyObjectProperty(search, 'name', 'equals', 'search', true)

WebUI.setText(search, servicesFile.getValue(1, 4))

WebUI.delay(1)

element = WebUI.modifyObjectProperty(findTestObject('Monitoring/Status details/Services/a_Service name'), 'text', 'equals', 
    servicesFile.getValue(1, 4), true)

WebUI.verifyElementNotPresent(findTestObject('Monitoring/Downtimes/img_Service downtime icon'), 1)

WebUI.click(element)

//These lines set the status of the service on CRITICAL and the state type on HARD
while (WebUI.verifyElementNotPresent(findTestObject('Monitoring/Status details/Services/CRITICAL status'),
	1, FailureHandling.OPTIONAL) || 
	WebUI.verifyElementNotPresent(findTestObject('Monitoring/Status details/Services/td_HARD'), 1, FailureHandling.OPTIONAL)) {
	WebUI.delay(1)
	
    WebUI.click(findTestObject('Monitoring/Status details/Services/a_Submit results'))

    WebUI.selectOptionByLabel(findTestObject('Monitoring/Status details/Services/select_Check result'), 'CRITICAL', true)

	WebUI.click(findTestObject('Monitoring/Status details/Services/save result'))

    WebUI.delay(2)

    WebUI.refresh()
}

//************************************************Go to Status Details services page***********************************************//

CustomKeywords.'custom.NavigationMonitoring.accessStatusDetailsServices'()

WebUI.waitForPageLoad(3)

//*******************************************************Verify the downtime*******************************************************//

//This put the service status to 'All'
WebUI.selectOptionByValue(findTestObject('Monitoring/Status details/Services/select_Unhandled Problems'), 'svc', true)

//These lines search for the service affected by the downtime
search = WebUI.modifyObjectProperty(findTestObject('General/input_Search'), 'name', 'equals', 'host_search', true)

WebUI.setText(search, config.getValue('TimeIndicator', 1) + hostFile.getValue('hostName', 1) + '1')

search = WebUI.modifyObjectProperty(search, 'name', 'equals', 'search', true)

WebUI.setText(search, servicesFile.getValue(1, 4))

WebUI.delay(1)

WebUI.verifyElementPresent(findTestObject('Monitoring/Downtimes/img_Service downtime icon'), 3)

WebUI.click(findTestObject('Old menu/a_Logout'))

WebUI.closeBrowser()