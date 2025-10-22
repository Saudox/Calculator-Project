package com.calc.calc;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.transform.Result;
@CrossOrigin(origins = "http://localhost:4200")
@RestController

public class CalcController {
    private final CalcLogic calcLogic;
    public CalcController(CalcLogic calcLogic) {
        this.calcLogic = calcLogic;
    }
    @PostMapping("/api/calc")
    public ResultWrap calc(@RequestBody String expression) {
        try {
            return new ResultWrap((Math.round(calcLogic.evaluate(expression) * 100000.0) / 100000.0), "Success");
        }catch (ArithmeticException e){
            return new ResultWrap(null, e.getMessage());
        } catch (Exception e) {
            return new ResultWrap(null, "Invalid expression");
        }
    }

    @PostMapping("/api/sqrt")
    public ResultWrap sqrt(@RequestBody String expression) {
        try {
            return new ResultWrap(calcLogic.sqrt(expression), "Success");
        } catch (ArithmeticException e) {
            return new ResultWrap(null, e.getMessage());
        } catch (Exception e) {
            return new ResultWrap(null, "Invalid expression");
        }
    }

    @PostMapping("/api/pwrTwo")
    public ResultWrap pwrTwo(@RequestBody String expression) {
        try {
            return new ResultWrap(calcLogic.pwrTwo(expression), "Success");
        } catch (ArithmeticException e) {
            return new ResultWrap(null, e.getMessage());
        } catch (Exception e) {
            return new ResultWrap(null, "Invalid expression");
        }
    }

    @PostMapping("/api/recip")
    public ResultWrap recip(@RequestBody String expression) {
        try {
            return new ResultWrap(calcLogic.toReciprocal(expression), "Success");
        } catch (ArithmeticException e) {
            return new ResultWrap(null, e.getMessage());
        } catch (Exception e) {
            return new ResultWrap(null, "Invalid expression");
        }
    }

    @PostMapping("/api/percent")
    public ResultWrap percent(@RequestBody String expression) {
        try {
            return new ResultWrap(calcLogic.toPercent(expression), "Success");
        } catch (ArithmeticException e) {
            return new ResultWrap(null, e.getMessage());
        } catch (Exception e) {
            return new ResultWrap(null, "Invalid expression");
        }
    }

    @PostMapping("/api/negate")
    public ResultWrap negate(@RequestBody String expression) {
        try {
            return new ResultWrap(calcLogic.toNegate(expression), "Success");
        } catch (ArithmeticException e) {
            return new ResultWrap(null, e.getMessage());
        } catch (Exception e) {
            return new ResultWrap(null, "Invalid expression");
        }
    }
}
