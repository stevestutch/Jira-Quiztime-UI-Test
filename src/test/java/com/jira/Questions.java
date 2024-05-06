package com.jira;

public class Questions {
    public enum Question {
        QUESTION_1("What is the capital of France?", "Paris"),
        QUESTION_2("Who wrote 'Romeo and Juliet'?", "William Shakespeare"),
        QUESTION_3("What is the chemical symbol for water?", "H2O"),
        QUESTION_4("What is the chemical symbol for gold?", "Au"),
        QUESTION_5("Who painted the Mona Lisa?", "Leonardo da Vinci"),
        QUESTION_6("What is the largest mammal in the world?", "Blue Whale"),
        QUESTION_7("Who is credited with the theory of relativity?", "Albert Einstein"),
        QUESTION_8("What is the tallest mountain in the world?", "Mount Everest"),
        QUESTION_9("Which planet is known as the 'Red Planet'?", "Mars"),
        QUESTION_10("Who is the author of 'To Kill a Mockingbird'?", "Harper Lee"),
        QUESTION_11("What is the capital of Japan?", "Tokyo"),
        QUESTION_12("Who painted 'The Starry Night'?", "Vincent van Gogh"),
        QUESTION_13("What is the largest organ in the human body?", "Skin"),
        QUESTION_14("Who was the first woman to win a Nobel Prize?", "Marie Curie"),
        QUESTION_15("What is the largest ocean in the world?", "Pacific Ocean"),
        QUESTION_16("Who wrote the play 'Hamlet'?", "William Shakespeare"),
        QUESTION_17("What is the chemical symbol for oxygen?", "O"),
        QUESTION_18("Who is known as the 'Father of Computers'?", "Charles Babbage"),
        QUESTION_19("What is the longest river in the world?", "Nile River"),
        QUESTION_20("Who was the first President of the United States?", "George Washington"),
        QUESTION_21("What is the chemical symbol for carbon?", "C"),
        QUESTION_22("Who wrote '1984'?", "George Orwell"),
        QUESTION_23("Which planet is closest to the sun?", "Mercury"),
        QUESTION_24("Who developed the theory of evolution by natural selection?", "Charles Darwin"),
        QUESTION_25("What is the chemical symbol for sodium?", "Na"),
        QUESTION_26("Who is the author of 'Pride and Prejudice'?", "Jane Austen"),
        QUESTION_27("What is the chemical symbol for iron?", "Fe"),
        QUESTION_28("Who discovered penicillin?", "Alexander Fleming"),
        QUESTION_29("What is the smallest country in the world?", "Vatican City"),
        QUESTION_30("Who wrote 'The Catcher in the Rye'?", "J.D. Salinger"),
        QUESTION_31("What is the chemical symbol for silver?", "Ag"),
        QUESTION_32("Who painted 'The Persistence of Memory'?", "Salvador Dalí"),
        QUESTION_33("What is the deepest ocean in the world?", "Pacific Ocean"),
        QUESTION_34("Who invented the telephone?", "Alexander Graham Bell"),
        QUESTION_35("What is the chemical symbol for calcium?", "Ca"),
        QUESTION_36("Who wrote 'Frankenstein'?", "Mary Shelley"),
        QUESTION_37("What is the melting point of water?", "0 degrees Celsius"),
        QUESTION_38("Who discovered gravity?", "Isaac Newton"),
        QUESTION_39("What is the hardest natural substance on Earth?", "Diamond"),
        QUESTION_40("Who is known as the 'Father of Geometry'?", "Euclid");

        private final String question;
        private final String answer;

        Question(String question, String answer) {
            this.question = question;
            this.answer = answer;
        }

        public String getQuestion() {
            return question;
        }

        public String getAnswer() {
            return answer;
        }

        public static String getAnswerForQuestion(String question) {
            for (Question q : values()) {
                if (q.question.equals(question)) {
                    return q.answer;
                }
            }
            return "Couldn't find the answer.";
        }
    }
}
