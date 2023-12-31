package com.project.Restaurant.Reservation;

import com.project.Restaurant.Member.consumer.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor

@Controller
public class ReservationController {

    private final ReservationService reservationService;


//        @GetMapping("/reservation/request")
//        public String Request() {
//            return "Reservation/reservation_request";
//        }
//
//        @PostMapping("/reservation/request")
//        public String Request(@RequestParam String store, @RequestParam String customerId,
//                              @RequestParam String ownerId) {
//            Reservation reservation = new Reservation();
//            reservation.setStore(store);
//            reservation.setCustomerId(customerId);
//            reservation.setOwnerId(ownerId);
//            reservation.setReservationTime(LocalDateTime.now());
//            reservation.setStatus("에약요청");
//
//            reservationService.save(reservation);
//
//            if ("예약요청".equals(reservation.getStatus())) {
//                // 예약요청에 대한 추가 로직
//                // 예: 알림 메일 전송, 알림 메시지 등
//            } else if ("예약승인".equals(reservation.getStatus())) {
//                // 예약승인에 대한 추가 로직
//            } else if ("예약완료".equals(reservation.getStatus())) {
//                // 예약완료에 대한 추가 로직
//            }
//
//            return "redirect:/reservation/list";
//        }


    @GetMapping("/reservation/list")
    public String list(Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/member/login";
        }

        String loggedInUsername = principal.getName(); // 현재 로그인한 사용자의 ID

        // 현재 로그인한 사용자의 ID와 일치하는 예약 목록만 가져오기
        List<Reservation> reservationsList = this.reservationService.findByCustomerUsername(loggedInUsername);
        model.addAttribute("reservationList", reservationsList);

        return "Reservation/reservation_list";
    }
}
