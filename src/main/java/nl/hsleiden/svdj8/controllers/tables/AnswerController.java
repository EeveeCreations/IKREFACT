package nl.hsleiden.svdj8.controllers.tables;

import nl.hsleiden.svdj8.daos.AnswerDAO;
import nl.hsleiden.svdj8.daos.CategoryDAO;
import nl.hsleiden.svdj8.models.tables.Answer;
import nl.hsleiden.svdj8.models.tables.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AnswerController {

    @Autowired
    public final AnswerDAO answerDAO;

    public final CategoryDAO categoryDAO;

    public AnswerController(AnswerDAO answerDAO, CategoryDAO categoryDAO) {
        this.answerDAO = answerDAO;
        this.categoryDAO = categoryDAO;
    }

    @GetMapping(value = "/answer/all")
    public List<Answer> getAllAnswers() {
        return answerDAO.getAll();
    }

    @GetMapping(value = "/answer/{id}")
    public Answer getAnswer(@PathVariable final Long id) {
        return answerDAO.getById(id);
    }


    @PutMapping(value = "/answer/{id}")
    public Answer editAnswer(@RequestBody Answer editAnswer, @PathVariable Long id) throws Exception {
        Answer returnAnswer = answerDAO.getByIdOptional(id)
                .map(answer -> {
                    answer.setAnswerText(editAnswer.getAnswerText());
                    answer.setQuestionID(editAnswer.getQuestionID());
                    answer.setCategory(editAnswer.getCategory());
                    return answerDAO.addAnswer(answer);
                })
                .orElseThrow(() -> new Exception(
                        "No answer found with id " + id + "\""));
        List<Category> categories = new ArrayList<>();
        for (Category category : returnAnswer.getCategory()) {
            categories.add(categoryDAO.getById(category.getCategoryID()));
        }
        returnAnswer.setCategory(categories);
        return returnAnswer;
    }

    @PutMapping(value = "/answer")
    public Answer addAnswer(@RequestBody Answer newAnswer) {
        return answerDAO.addAnswer(newAnswer);
    }

    @DeleteMapping("/answer/{id}")
    public void deleteAnswer(@PathVariable Long id) {
        answerDAO.deleteByAnswerId(id);
    }
}
