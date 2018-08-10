package com.tcs.hack;

import com.tcs.hack.dto.BookingDTO;
import com.tcs.hack.model.Booking;
import com.tcs.hack.model.Resource;
import com.tcs.hack.repository.BookingRepository;
import com.tcs.hack.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@org.springframework.stereotype.Service
public class Service {

    @Autowired
    ResourceRepository resourceRepository;

    @Autowired
    BookingRepository bookingRepository;

    public List<Resource> getAllResources(){

        return (List<Resource>)resourceRepository.findAll();
    }

    public Resource fetchSpecificResource(Integer id){

        return resourceRepository.findOne(id);
    }

    public ResponseEntity<Resource> addResource(Resource resource) {
      /*  if(resourceRepository.exists(resource.getResourceId())) {
            Resource fetchedResorce = resourceRepository.findOne(resource.getResourceId());
            fetchedResorce.setResourceName(resource.getResourceName());
            resourceRepository.save(fetchedResorce);

        }*/
       Resource addedResource = resourceRepository.save(resource);
       return  new ResponseEntity<Resource>(addedResource, HttpStatus.CREATED);
    }

    public ResponseEntity<Resource> deleteResource(Resource resource) {
        if(resourceRepository.exists(resource.getResourceId()))
            resourceRepository.delete(resource.getResourceId());
        return new ResponseEntity<Resource>(resource, HttpStatus.OK);
    }

    public List<Booking> fetchReservations(){
       return (List<Booking>) bookingRepository.findAll();
    }

    public ResponseEntity<?>  addReservation(BookingDTO bookingDTO) throws ParseException {
        int available = bookingRepository.findAvailability(bookingDTO.getResourceId(),
                 new SimpleDateFormat("yyyy-MM-dd").parse(bookingDTO.getBookingDate()), bookingDTO.getBookingSlot());
        if(available==0) {
            Booking booking = new Booking();
            Resource resource = fetchSpecificResource
                    (bookingDTO.getResourceId());
            booking.setBookingDate( new SimpleDateFormat("yyyy-MM-dd").parse(bookingDTO.getBookingDate()));
            booking.setBookingSlot(bookingDTO.getBookingSlot());
            booking.setResource(resource);
            Booking savedBooking = bookingRepository.save(booking);
            return new ResponseEntity<Booking>(savedBooking, HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>("Resource not available",HttpStatus.OK);
        }
    }

}
