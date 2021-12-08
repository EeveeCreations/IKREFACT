package nl.hsleiden.svdj8.daos;

import javassist.NotFoundException;
import nl.hsleiden.svdj8.models.tables.Question;
import nl.hsleiden.svdj8.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Component
public class QuestionDAO {

    @Autowired
    private QuestionRepository questionRepository;

    public QuestionDAO(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public List<Question> getAll() {
        ArrayList<Question> questions = (ArrayList<Question>) this.questionRepository.findAll();
        questions.sort(Comparator.comparingLong(Question::getQuestionID));
        return questions;
    }

    public Question getById(long id) {
        Optional<Question> optionalQuestion = questionRepository.findById(id);
        if (optionalQuestion.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Question with the id: " + id + " not found");
        }
        return optionalQuestion.get();
    }

    public Optional<Question> getByIdOptional(long id) {
        Optional<Question> optionalQuestion = questionRepository.findById(id);
        return optionalQuestion;
    }

    public void deleteQuestion(long id) {
        questionRepository.deleteById(id);
    }

    public Question addQuestion(Question newQuestion) {
        return questionRepository.save(newQuestion);
    }
}
