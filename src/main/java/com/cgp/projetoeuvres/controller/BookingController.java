package com.cgp.projetoeuvres.controller;

import com.cgp.projetoeuvres.entity.*;
import com.cgp.projetoeuvres.exception.ResourceNotFoundException;
import com.cgp.projetoeuvres.repository.AdherentRepository;
import com.cgp.projetoeuvres.repository.BookingRepository;
import com.cgp.projetoeuvres.repository.OwnerRepository;
import com.cgp.projetoeuvres.repository.WorkForSaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Controller
@RequestMapping("/bookings")
public class BookingController {

    private BookingRepository bookingRepository;
    private WorkForSaleRepository workForSaleRepository;
    private AdherentRepository adherentRepository;

    @Autowired
    public BookingController(BookingRepository bookingRepository, WorkForSaleRepository workForSaleRepository,
                             AdherentRepository adherentRepository) {
        this.bookingRepository = bookingRepository;
        this.workForSaleRepository = workForSaleRepository;
        this.adherentRepository = adherentRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String findAllBookings(Model model) {
        model.addAttribute("bookings", bookingRepository.findAll());
        return "booking/booking-list";
    }

    @RequestMapping("/add")
    public String displayAddForm(Model model) {
        model.addAttribute("worksforsale", workForSaleRepository.findAll());
        model.addAttribute("adherents", adherentRepository.findAll());
        return "booking/booking-form";
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody
    Booking addBooking(@RequestParam Date bookingDate, @RequestParam String status, @RequestParam String workForSaleId,
                       @RequestParam String adherentId) {
        WorkForSale workForSale = workForSaleRepository.findById(Integer.valueOf(workForSaleId)).orElseThrow(()
                -> new ResourceNotFoundException("WorkForSale", "id", workForSaleId));
        Adherent adherent = adherentRepository.findById(Integer.valueOf(adherentId)).orElseThrow(()
                -> new ResourceNotFoundException("Adherent", "id", adherentId));

        Booking booking = new Booking();
        booking.setBookingDate(bookingDate);
        booking.setStatus(status);
        booking.setWorkForSale(workForSale);
        booking.setAdherent(adherent);

        //workForSale.setState("R");
        //workForSaleRepository.save(workForSale);
        //bookingRepository.
        return bookingRepository.save(booking);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{adherentId}/{workForSaleId}")
    public @ResponseBody Booking updateBooking(@PathVariable(value = "adherentId") String adherentId,
                          @PathVariable(value = "workForSaleId") String workForSaleId,
                          @RequestParam String bookingDate, @RequestParam String status) {

        Adherent adherent = adherentRepository.findById(Integer.valueOf(adherentId)).orElseThrow(()
                -> new ResourceNotFoundException("Adherent", "id", adherentId));

        WorkForSale workForSale = workForSaleRepository.findById(Integer.valueOf(workForSaleId)).orElseThrow(()
                -> new ResourceNotFoundException("WorkForSale", "id", workForSaleId));


       Booking booking = bookingRepository.findById(new BookingKey(workForSale.getId(), adherent.getId()))
               .orElseThrow(() -> new ResourceNotFoundException("BookingKey", "workForSaleId - adherentId", workForSaleId + " - " + adherentId));

        DateFormat formatter;

        formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedBookingDate = null;
        try {
            parsedBookingDate = (Date) formatter.parse(bookingDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        booking.setBookingDate(parsedBookingDate);
        booking.setStatus(status);
        booking.setWorkForSale(workForSale);
        booking.setAdherent(adherent);

        workForSale.setState("R");
        workForSaleRepository.save(workForSale);

        return bookingRepository.save(booking);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{adherentId}/{workForSaleId}")
    public @ResponseBody Booking deleteWorkForSale(@PathVariable(value = "adherentId") String adherentId,
                              @PathVariable(value = "workForSaleId") String workForSaleId) {

        Adherent adherent = adherentRepository.findById(Integer.valueOf(adherentId)).orElseThrow(()
                -> new ResourceNotFoundException("Adherent", "id", adherentId));

        WorkForSale workForSale = workForSaleRepository.findById(Integer.valueOf(workForSaleId)).orElseThrow(()
                -> new ResourceNotFoundException("WorkForSale", "id", workForSaleId));


        Booking booking = bookingRepository.findById(new BookingKey(workForSale.getId(), adherent.getId()))
                .orElseThrow(() -> new ResourceNotFoundException("BookingKey", "workForSaleId - adherentId", workForSaleId + " - " + adherentId));

        bookingRepository.delete(booking);

        return booking;
    }
}