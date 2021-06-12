package com.atm.controller;

import com.atm.domain.Card;

import javax.validation.Valid;

import com.atm.domain.User;
import com.atm.service.CardService;
import com.atm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
public class CardController {

    @Autowired
    private CardService cardService;

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/add/card", method = RequestMethod.GET)
    public String getCard(Model model) {
        User user = getUser();
        if (user.getCard() == null) {
            return "createCard";
        } else {
            return "redirect:/replenishment";
        }
    }

    @RequestMapping(value = "/add/card", method = RequestMethod.POST)
    public ModelAndView createCard(@Valid @ModelAttribute("cardForm") Card card) {

        User user = getUser();
        card.setUser(user);
        cardService.save(card);
        return new ModelAndView("redirect:/home");
    }

    @RequestMapping(value ="/create-card", method = RequestMethod.GET)
    public ModelAndView createPeriodical() {

        User user = getUser();
        if (user.getCard() == null ) {
            return new ModelAndView("createCard", "cardForm", new Card());
        } else {
            return new ModelAndView("redirect:/cards");
        }
    }

    @RequestMapping(value = "/cards", method = RequestMethod.GET)
    public String getAll(Model model) {
        User user = getUser();
        model.addAttribute("card", user.getCard());
        return "cards";
    }

    @RequestMapping(value = "/replenishment", method = RequestMethod.GET)
    public String getReplenishment(Model model) {
        User user = getUser();
        model.addAttribute("card", user.getCard());
        model.addAttribute("replenishmentForm", user.getCard());
        return "replenishment";
    }

    @RequestMapping(value = "/replenishment", method = RequestMethod.POST)
    public String addBalance(@ModelAttribute("replenishmentForm") Card add) {
        User user = getUser();
        Card card = user.getCard();
        card.setBalance(card.getBalance() + add.getBalance());
        cardService.saveChange(card);
        return "redirect:/cards";
    }

    private User getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = auth.getName();
        return userService.findByEmail(userEmail);
    }

    @RequestMapping(value = "/removal", method = RequestMethod.GET)
    public String getRemoval(Model model) {
        User user = getUser();
        model.addAttribute("card", user.getCard());
        model.addAttribute("removalForm", user.getCard());
        return "removal";
    }

    @RequestMapping(value = "/removal", method = RequestMethod.POST)
    public String removal(@ModelAttribute("removalForm") Card add) {
        User user = getUser();
        Card card = user.getCard();
        card.setBalance(card.getBalance() - add.getBalance());
        cardService.saveChange(card);
        return "redirect:/cards";
    }

    @RequestMapping(value = "/replenishment/to/the/user", method = RequestMethod.GET)
    public String getReplenishmentToTheUser(Model model) {
        Card card = new Card();
        model.addAttribute("replenishmentToTheUserForm", card);
        return "replenishmentToTheUser";
    }

    @RequestMapping(value = "/replenishment/to/the/user", method = RequestMethod.POST)
    public String replenishmentToTheUser(@ModelAttribute("replenishmentToTheUserForm") Card add) {
        Card card = cardService.findCardByNumber(add.getNumber());
        card.setBalance(card.getBalance()+add.getBalance());
        cardService.saveChange(card);
        return "redirect:/home";
    }

}
