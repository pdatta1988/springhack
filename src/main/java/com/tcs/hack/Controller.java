package com.tcs.hack;

import com.tcs.hack.dto.BookingDTO;
import com.tcs.hack.dto.ReservationsDTO;
import com.tcs.hack.model.Booking;
import com.tcs.hack.model.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tcs/hack/v1")
public class Controller {
    @Autowired
    private Service service;

    @GetMapping("/resources")
    public @ResponseBody
    List<Resource> getAvailableResource(){
        return service.getAllResources();
    }
    @GetMapping("/resources/{id}")
    public @ResponseBody
    Resource fetchSpecificResource(@PathVariable Integer id) {
        return service.fetchSpecificResource(id);
    }
    @PostMapping("/resources")
    public @ResponseBody
    ResponseEntity<Resource> addResource(@RequestBody Resource resource){
        return service.addResource(resource);
    }
    @DeleteMapping("/resources")
    public @ResponseBody
    ResponseEntity<Resource> deleteResource(@RequestBody Resource resource){
        return service.deleteResource(resource);
    }

    @GetMapping("/reservations")
    public @ResponseBody
    List<ReservationsDTO> fetchAllReservations(){
        List<ReservationsDTO> reservationsDTOS = new ArrayList<>();
        List<Booking> bookingList = service.fetchReservations();
        bookingList.forEach(booking -> {
            ReservationsDTO reservationsDTO = new ReservationsDTO();
            reservationsDTO.setBookingDate(String.valueOf(booking.getBookingDate()));
            reservationsDTO.setBookingSlot(booking.getBookingSlot());
            reservationsDTO.setResourceName(booking.getResource().getResourceName());
            reservationsDTOS.add(reservationsDTO);
        });
        return reservationsDTOS;

    }

    @PostMapping("/reservations")
    public @ResponseBody
    ResponseEntity<?> addNewReservation(@RequestBody BookingDTO bookingDTO) throws ParseException {
        return service.addReservation(bookingDTO);
    }


}