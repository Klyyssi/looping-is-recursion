(ns looping-is-recursion)

(defn power [base exp]
  (let [helper (fn [acc n k]
                 (if (zero? k)
                   acc
                   (recur (* acc n) n (dec k))))]
    (helper 1 base exp)))

(defn last-element [a-seq]
  (let [helper (fn [acc b-seq]
                 (if (empty? b-seq) 
                   acc
                   (recur (first b-seq) (rest b-seq))))]
    (helper (first a-seq) (rest a-seq))))

(defn seq= [seq1 seq2]
  (let [helper (fn [seq1 seq2] 
                 (cond 
                   (not (= (first seq1) (first seq2))) false
                   (not (= (count seq1) (count seq2))) false
                   (and (empty? seq1) (empty? seq2)) true
                   :else (recur (rest seq1) (rest seq2))))]
    (helper seq1 seq2)))

(defn find-first-index [pred a-seq]
  (loop [acc 0
         b-seq a-seq]
    (cond 
      (empty? b-seq) nil
      (pred (first b-seq)) acc
      :else (recur (+ acc 1) (rest b-seq)))))

(defn avg [a-seq]
  (loop [sum-so-far 0
         items-count 0
         b-seq a-seq]
    (if (empty? b-seq)
      (/ sum-so-far items-count)
      (recur (+ (first b-seq) sum-so-far) (+ items-count 1) (rest b-seq)))))

(defn toggle [a-set elem] 
  (set (if (contains? a-set elem) 
    (disj a-set elem) (conj a-set elem))))

(defn parity [a-seq]
  (loop [acc #{}
         b-seq a-seq]
    (if (empty? b-seq)
      acc
      (recur (toggle acc (first b-seq)) (rest b-seq)))))

(defn fast-fibo [n]
  (loop [so-far 0
         prev-prev 0
         prev 1]
    (if (<= n so-far)
      prev-prev
      (recur (+ so-far 1) prev (+ prev-prev prev)))))

(defn cut-at-repetition [a-seq]
  (loop [b-seq a-seq
         acc []]
    (cond
      (empty? b-seq) acc
      (not (= nil (get (set acc) (first b-seq)))) acc
      :else (recur (rest b-seq) (conj acc (first b-seq))))))



