package uk.gov.dhsc.htbhf.card.controller;

import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import uk.gov.dhsc.htbhf.card.model.CardBalance;
import uk.gov.dhsc.htbhf.card.model.CardRequestDTO;
import uk.gov.dhsc.htbhf.card.model.CardResponse;
import uk.gov.dhsc.htbhf.card.model.DepositFundsRequestDTO;

import java.util.UUID;
import javax.validation.Valid;

@RestController
@RequestMapping("v1/cards")
@AllArgsConstructor
@Slf4j
@Api
public class CardController {

    @PostMapping
    @ApiOperation("Create a new card")
    @ApiResponses({@ApiResponse(code = 201, message = "The response to the create card request")})
    @ResponseStatus(HttpStatus.CREATED)
    public CardResponse createCard(@RequestBody @Valid @ApiParam("Details of the new card request") CardRequestDTO cardRequest) {
        log.debug("Received new card request");
        return CardResponse.builder()
                .cardAccountId(UUID.randomUUID().toString())
                .build();
    }

    @PostMapping("/{cardId}/deposit")
    @ApiOperation("Deposit funds onto a card")
    @ApiResponses({@ApiResponse(code = 200, message = "Payment reference")})
    public void depositFunds(@PathVariable("cardId") String cardId,
                             @RequestBody @Valid @ApiParam("Deposit funds request information") DepositFundsRequestDTO depositFundsRequest) {
        log.debug("Received new deposit funds request");
    }

    @GetMapping("/{cardId}/balance")
    @ApiOperation("Get balance for a card")
    @ApiResponses({@ApiResponse(code = 200, message = "Card balance", response = CardBalance.class)})
    public CardBalance getBalance(@PathVariable("cardId") String cardId) {
        log.debug("Received request for card balance");
        return CardBalance.builder()
                .availableBalanceInPence(10)
                .ledgerBalanceInPence(10)
                .build();
    }


}
