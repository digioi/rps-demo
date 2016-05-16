; returns nil if tied else true/false
(defn is-winner [choice1 choice2] 
  (cond
    (= choice1 choice2)                           nil
    (and (= choice1 :rock) (= choice2 :scissor))  true
    (and (= choice1 :rock) (= choice2 :paper))    false
    (and (= choice1 :paper) (= choice2 :rock))    true
    (and (= choice1 :paper) (= choice2 :scissor)) false
    (and (= choice1 :scissor) (= choice2 :paper)) true
    (and (= choice1 :scissor) (= choice2 :rock))  false))

(def score (atom 0)) ; Starting score
(defn make-choice [choice] 
  (let [player-choice (keyword choice)     ; cast choice to keyword 
        choices #{:rock :paper :scissor}]  ; choices 
    (if (contains? choices player-choice)  ; verify choice is within constraints
      (let [comp-choice (rand-nth (seq choices)) ; randomly choose for computer
            winner      (is-winner player-choice comp-choice)]
        (println "Computer chose: " (name comp-choice))
        (cond
          (true?  winner) (swap! score inc)   ; increment score if was a winner
          (false? winner) (swap! score dec)   ; decrement score if was not a winner
          (nil?   winner) (println "Tied")))  ; Just print that it was a tie
        (println "Score: " @score))           ; print current score
      (println "invalid choice"))))
