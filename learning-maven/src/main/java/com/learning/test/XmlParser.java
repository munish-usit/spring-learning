package com.learning.test;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XmlParser {

	private static String getEventAttributeValue(String attributeName, String xmlData) throws ParserConfigurationException {
    	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    	DocumentBuilder db = null;
    	try {
    	    db = dbf.newDocumentBuilder();
    	    InputSource is = new InputSource();
    	    is.setCharacterStream(new StringReader(xmlData));
    	    try {
    	        Document doc = db.parse(is);
    	        NodeList nodeList = doc.getElementsByTagName(attributeName);
    	        if(nodeList != null) {
    	            Node first = nodeList.item(0);
        	        NamedNodeMap attributeMap = first.getAttributes();
        	        Node item = attributeMap.getNamedItem("Value");
        	        return item.getNodeValue();
    	        }
    	    } catch (SAXException | IOException e) {
    	        // handle SAXException
    	    	return "";
    	    }
    	} catch (ParserConfigurationException e1) {
    	    return "";
    	}
		return xmlData;
    }
	
	public static void main(String [] args) {
		String xmlData = "<?xml version=\"1.0\" encoding=\"utf-8\" standalone=\"yes\" ?>\r\n"
				+ "<LoadSetToPlanned ApplyResourceConstraints=\"bTRUE\" DivisionCode=\"WHS\" EventDateTime=\"12/17/2014 15:28:04\" EventName=\"LoadSetToPlanned\" EventOccurredDateTime=\"12/17/2014 15:28:04\" EventOccurredTimeZoneOffset=\"0.000000\" EventReportedDateTime=\"12/17/2014 15:28:04\" EventUser=\"WHS\" ExtendedWeightDistance=\"0.651300\" LoadDescription=\"Keep Stop's Schedule Data TRUE\" LoadEndDateTime=\"05/31/2007 10:00:00\" LoadEndDateTimeTimeZoneOffset=\"0.000000\" LoadScheduleCompleted=\"bTRUE\" LoadSequenceNumber=\"0\" LoadStartDateTime=\"05/29/2007 19:30:00\" LoadStartDateTimeTimeZoneOffset=\"0.000000\" LogisticsGroup=\"*DFT\" PrimaryScheduleHardCommitted=\"bFALSE\" RatingValid=\"bTRUE\" ReasonCode=\"PLND\" ReasonCodeDescription=\"Planned\" RelatedDepartureTimeZoneOffset=\"0.000000\" SendTenderByEDI=\"bFALSE\" SystemLoadID=\"69768\" TotalDriveDistance=\"52.100000\" TotalDriveTime=\"0.870000\" TotalNumberOfShipments=\"1\" TotalNumberOfStops=\"2\" TripID=\"0\" VesselArrivalTimeZoneOffset=\"0.000000\" VesselDepartureTimeZoneOffset=\"0.000000\">\r\n"
				+ "\r\n"
				+ "  <Carrier CarrierCode=\"WHS-S-D\" CarrierName=\"WHS-S-D (DESTINATION)\">\r\n"
				+ "    <POD Value=\"PODDLVNTFRQ\"/>\r\n"
				+ "    <SCAC Value=\"*DFT\"/>\r\n"
				+ "    <CarrierBusinessHours CarrierBusinessHoursTimeZoneOffset=\"0.000000\"/>\r\n"
				+ "  </Carrier>\r\n"
				+ "\r\n"
				+ "  <Service ServiceCode=\"TL\" ServiceExternalID1=\"TLS\" ServiceExternalID2=\"ZZZ\"/>\r\n"
				+ "\r\n"
				+ "  <EquipmentTypeDetail EquipmentType=\"*DFT\" EquipmentTypeExternalID=\"TH\"/>\r\n"
				+ "\r\n"
				+ "  <TariffService SystemTariffID=\"8920\">\r\n"
				+ "    <AutoAcceptTender Value=\"bFALSE\"/>\r\n"
				+ "  </TariffService>\r\n"
				+ "\r\n"
				+ "  <SuspendedReasonEnumVal Value=\"NOT SUSPEND\"/>\r\n"
				+ "\r\n"
				+ "  <AmountDetail ChargeAmount=\"12.400000\"/>\r\n"
				+ "\r\n"
				+ "  <CurrencyType Value=\"USD\"/>\r\n"
				+ "\r\n"
				+ "  <SystemLoadStatus Value=\"LL_DPLANNED\"/>\r\n"
				+ "\r\n"
				+ "  <PaymentTerm Value=\"PREPAID\"/>\r\n"
				+ "\r\n"
				+ "  <LoadUOM>\r\n"
				+ "    <LoadVolumeUOM Value=\"FT\"/>\r\n"
				+ "    <LoadWeightUOM Value=\"LB\"/>\r\n"
				+ "  </LoadUOM>\r\n"
				+ "\r\n"
				+ "  <LoadTotals LoadPieces=\"1.000000\" LoadSkids=\"0.000000\" LoadVolume=\"0.000000\" LoadWeight=\"25.000000\"/>\r\n"
				+ "\r\n"
				+ "  <LoadBookingServiceType Value=\"0\"/>\r\n"
				+ "\r\n"
				+ "  <VesselDepartureLocationTypeEnumVal Value=\"0\"/>\r\n"
				+ "\r\n"
				+ "  <VesselArrivalLocationTypeEnumVal Value=\"0\"/>\r\n"
				+ "\r\n"
				+ "  <RelatedDepartureLocationTypeEnumVal Value=\"0\"/>\r\n"
				+ "\r\n"
				+ "</LoadSetToPlanned>";
		try {
			getEventAttributeValue("SuspendedReasonEnumVal",xmlData);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
