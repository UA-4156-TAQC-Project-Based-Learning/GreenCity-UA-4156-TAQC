package com.greencity.jdbc.entity.eventsEntity;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class EventDateLocationEntity {
    public static final String SELECT_ALL = "SELECT * FROM events_dates_locations";
    public static final String FIND_BY_EVENT_ID = "SELECT * FROM events_dates_locations WHERE event_id = %d";

    private Long id;
    private Long eventId;
    private Timestamp startDate;
    private Timestamp finishDate;
    private String onlineLink;
    private Double latitude;
    private Double longitude;
    private String streetEn;
    private String streetUa;
    private String houseNumber;
    private String cityEn;
    private String cityUa;
    private String regionEn;
    private String regionUa;
    private String countryEn;
    private String countryUa;

    public static EventDateLocationEntity parseRow(List<String> row) {
        EventDateLocationEntity eventDateLocationEntity = new EventDateLocationEntity();

        eventDateLocationEntity.setId(row.get(0) == null ? null : Long.parseLong(row.get(0)));
        eventDateLocationEntity.setEventId(row.get(1) == null ? null : Long.parseLong(row.get(1)));

        String startDateStr = row.get(2);
        if (startDateStr != null) {
            // Замінюємо пробіл на 'T', щоб відповідати ISO-формату, видаляємо часову зону, якщо є
            startDateStr = startDateStr.replace(" ", "T").replaceAll("(\\+\\d{2}:?\\d{2})|(Z)$", "");
            try {
                eventDateLocationEntity.setStartDate(Timestamp.valueOf(startDateStr));
            } catch (IllegalArgumentException e) {
                eventDateLocationEntity.setStartDate(null); // або обробити помилку інакше
            }
        } else {
            eventDateLocationEntity.setStartDate(null);
        }

        String finishDateStr = row.get(3);
        if (finishDateStr != null) {
            finishDateStr = finishDateStr.replace(" ", "T").replaceAll("(\\+\\d{2}:?\\d{2})|(Z)$", "");
            try {
                eventDateLocationEntity.setFinishDate(Timestamp.valueOf(finishDateStr));
            } catch (IllegalArgumentException e) {
                eventDateLocationEntity.setFinishDate(null);
            }
        } else {
            eventDateLocationEntity.setFinishDate(null);
        }

        eventDateLocationEntity.setOnlineLink(row.get(4));
        eventDateLocationEntity.setLatitude(row.get(5) == null ? null : Double.parseDouble(row.get(5)));
        eventDateLocationEntity.setLongitude(row.get(6) == null ? null : Double.parseDouble(row.get(6)));
        eventDateLocationEntity.setStreetEn(row.get(7));
        eventDateLocationEntity.setStreetUa(row.get(8));
        eventDateLocationEntity.setHouseNumber(row.get(9));
        eventDateLocationEntity.setCityEn(row.get(10));
        eventDateLocationEntity.setCityUa(row.get(11));
        eventDateLocationEntity.setRegionEn(row.get(12));
        eventDateLocationEntity.setRegionUa(row.get(13));
        eventDateLocationEntity.setCountryEn(row.get(14));
        eventDateLocationEntity.setCountryUa(row.get(15));
        return eventDateLocationEntity;
    }
}
