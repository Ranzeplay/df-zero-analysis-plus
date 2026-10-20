package seminar.dataflow.zeroanalysis;

/**
 * This enum represents the possible values of the zero analysis for a variable.
 * This is the extended lattice: besides -, 0 and +, it can say that a variable is <= 0, >= 0 or != 0.
 */
public enum ZeroAbstractValue {

    /**
     * We don't have information about the variable.
     */
    BOTTOM("bottom"),

    /**
     * The variable is negative (< 0).
     */
    NEGATIVE("negative"),

    /**
     * The variable is zero.
     */
    ZERO("zero"),

    /**
     * The variable is positive (> 0).
     */
    POSITIVE("positive"),

    /**
     * The variable is negative or zero (<= 0).
     */
    NON_POSITIVE("non-positive"),

    /**
     * The variable is positive or zero (>= 0).
     */
    NON_NEGATIVE("non-negative"),

    /**
     * The variable is not zero (< 0 or > 0).
     */
    NOT_ZERO("not-zero"),

    /**
     * The variable is top (can be any value).
     */
    TOP("top");


    /**
     * The name of the ZeroAbstractValue.
     */
    private final String name;

    @Override
    public String toString() {
        return this.name;
    }

    ZeroAbstractValue(String name) {
        this.name = name;
    }

    /**
     * Returns the result of the addition between this ZeroAbstractValue and another.
     * @param another the other ZeroAbstractValue.
     * @return the result of the addition.
     */
    public ZeroAbstractValue add(ZeroAbstractValue another) {
        // We should have 64 cases in total
        // 15 cases
         if (this.equals(BOTTOM)|| another == BOTTOM) {
            return BOTTOM;
         }
         // 15 cases
         if (this.equals(TOP) || another == TOP) {
            return TOP;
         }
         // 8
         if (this.equals(ZERO)) {
            return another;
         }// 7
         if (another.equals(ZERO)) {
            return this;
         }
         // 1
         if (this.equals(POSITIVE) && another.equals(POSITIVE)) {
            return POSITIVE;
         }
         // 1
         if (this.equals(NEGATIVE) && another.equals(NEGATIVE)) {
            return NEGATIVE;
         }
         // 2
         if ((this.equals(POSITIVE) && another.equals(NEGATIVE)) || (this.equals(NEGATIVE) && another.equals(POSITIVE))) {
            return TOP;
         }
         // 2
         if ((this.equals(POSITIVE) && another.equals(NON_POSITIVE)) || (this.equals(NON_POSITIVE) && another.equals(POSITIVE))) {
            return TOP;
         }
         // 2
         if ((this.equals(NEGATIVE) && another.equals(NON_NEGATIVE)) || (this.equals(NON_NEGATIVE) && another.equals(NEGATIVE))) {
                return TOP;
         }
         //2 
         if ((this.equals(NON_POSITIVE) && another.equals(NON_NEGATIVE)) || (this.equals(NON_NEGATIVE) && another.equals(NON_POSITIVE))) {
            return TOP;
         }
         // 2
         if ((this.equals(POSITIVE) && another.equals(NON_NEGATIVE)) || (this.equals(NON_NEGATIVE) && another.equals(POSITIVE))) {
             return POSITIVE;
         }
         // 2
         if ((this.equals(NEGATIVE) && another.equals(NON_POSITIVE)) || (this.equals(NON_POSITIVE) && another.equals(NEGATIVE))) {
             return NEGATIVE;
         }
         if ((this.equals(NON_POSITIVE) && another.equals(NON_POSITIVE))) {
            return NON_POSITIVE;
         }
         if ((this.equals(NON_NEGATIVE) && another.equals(NON_NEGATIVE))) {
            return NON_NEGATIVE;
         }
         if ((this.equals(NOT_ZERO) && another.equals(NOT_ZERO))) {
            return TOP;
         }
         if ((this.equals(NOT_ZERO) && another.equals(POSITIVE)) || (this.equals(POSITIVE) && another.equals(NOT_ZERO))) {
            return TOP;
         }
         if ((this.equals(NOT_ZERO) && another.equals(NEGATIVE)) || (this.equals(NEGATIVE) && another.equals(NOT_ZERO))) {
            return TOP;
         }
         if ((this.equals(NOT_ZERO) && another.equals(NON_POSITIVE)) || (this.equals(NON_POSITIVE) && another.equals(NOT_ZERO))) {
            return TOP;
         }
         if ((this.equals(NOT_ZERO) && another.equals(NON_NEGATIVE)) || (this.equals(NON_NEGATIVE) && another.equals(NOT_ZERO))) {
            return TOP;
         }
         return TOP;
        



    }

    /**
     * Returns the result of the division between this ZeroAbstractValue and another.
     * @param another the other ZeroAbstractValue.
     * @return the result of the division.
     */
    public ZeroAbstractValue divideBy(ZeroAbstractValue another) {
        if (this.equals(BOTTOM)|| another.equals(BOTTOM)) {
            return BOTTOM;
        }
        if (another.equals(ZERO)) {
            return BOTTOM; // Division by zero is undefined
        }
        if (this.equals(ZERO)) {
            return ZERO; // 0 divided by anything is 0
        }
        if(another.equals(NOT_ZERO)) {
            return TOP; // The denominator can have either sign, and division may truncate to zero.
        }
        if (another.equals(POSITIVE) || another.equals(NON_NEGATIVE)) {
            if (this.equals(NEGATIVE)) return NON_POSITIVE;
            if (this.equals(POSITIVE)) return NON_NEGATIVE;
            if (this.equals(NOT_ZERO)) return TOP;
            return this;
        }
        if (another.equals(NEGATIVE) || another.equals(NON_POSITIVE)) {
            if (this.equals(NEGATIVE)) return NON_NEGATIVE;
            if (this.equals(POSITIVE)) return NON_POSITIVE;
            if (this.equals(NOT_ZERO)) return TOP;
            return this.negate();
        }
        return TOP;
    }

    /**
     * Returns the result of the multiplication between this ZeroAbstractValue and another.
     * @param another the other ZeroAbstractValue.
     * @return the result of the multiplication.
     */
    public ZeroAbstractValue multiplyBy(ZeroAbstractValue another) {
        if (another.equals(BOTTOM) || this.equals(BOTTOM)) {
            return BOTTOM;
        }
        if (this.equals(ZERO) || another.equals(ZERO)) {
            return ZERO;
        }
        if (this.equals(POSITIVE)) {
            return another;
        }
        if (this.equals(NEGATIVE)) {
            return another.negate();
        }
        if (this.equals(NON_POSITIVE)) {
            if (another.equals(POSITIVE)) {
                return NON_POSITIVE;
            } else if (another.equals(NEGATIVE)) {
                return NON_NEGATIVE;
            } else if (another.equals(NON_POSITIVE)) {
                return NON_NEGATIVE;
            } else if (another.equals(NON_NEGATIVE)) {
                return NON_POSITIVE;
            } else if (another.equals(NOT_ZERO)) {
                return TOP;
            }
        }
        if (this.equals(NON_NEGATIVE)) {
            if (another.equals(POSITIVE)) {
                return NON_NEGATIVE;
            } else if (another.equals(NEGATIVE)) {
                return NON_POSITIVE;
            } else if (another.equals(NON_POSITIVE)) {
                return NON_POSITIVE;
            } else if (another.equals(NON_NEGATIVE)) {
                return NON_NEGATIVE;
            } else if (another.equals(NOT_ZERO)) {
                return TOP;
            }
        }
        if (this.equals(NOT_ZERO)) {
            if (another.equals(POSITIVE) || another.equals(NEGATIVE) || another.equals(NOT_ZERO)) {
                return NOT_ZERO;
            }
        }
        // if (this.equals(TOP) || another.equals(TOP)) {
        //     return TOP;
        // }
        return TOP; // Default case
    }

    /**
     * Returns the result of the subtraction between this ZeroAbstractValue and another.
     * @param another the other ZeroAbstractValue.
     * @return the result of the subtraction.
     */
    public ZeroAbstractValue substract(ZeroAbstractValue another) {
        
        if (this.equals(BOTTOM) || another.equals(BOTTOM)) {
            return BOTTOM;
        }
        if (this.equals(ZERO)) {
            return another.negate();
        }
        if (another.equals(ZERO)) {
            return this;
        }
        if (this.equals(POSITIVE)) {
            if (another.equals(POSITIVE)) {
                return TOP;
            } else if (another.equals(NEGATIVE)) {
                return POSITIVE;
            } else if (another.equals(NON_POSITIVE)) {
                return POSITIVE;
            } else if (another.equals(NON_NEGATIVE)) {
                return TOP;
            } else if (another.equals(NOT_ZERO)) {
                return TOP;
            }
        }
        if (this.equals(NEGATIVE)) {
            if (another.equals(POSITIVE)) {
                return NEGATIVE;
            } else if (another.equals(NEGATIVE)|| another.equals(NON_POSITIVE) || another.equals(NOT_ZERO)) {
                return TOP;
            } else if (another.equals(NON_NEGATIVE)) {
                return NEGATIVE;
            
            }
        }
        // For the remaining cases, subtract by negating the right operand and adding.
        return this.add(another.negate());
    }

    /**
     * Returns the result of the merge between this ZeroAbstractValue and another.
     * @param another the other ZeroAbstractValue.
     * @return the result of the merge.
     */
    public ZeroAbstractValue merge(ZeroAbstractValue another) {
        if (this.equals(another)){
            return this;
        }
        if (this.equals(BOTTOM)) {
            return another;
        }
        if (another.equals(BOTTOM)) {
            return this;
        }
        if (this.equals(TOP)||another.equals(TOP)) {
            return TOP;
        }
        if ((this.equals(ZERO) && another.equals(POSITIVE)) || (this.equals(POSITIVE) && another.equals(ZERO))) {
            return NON_NEGATIVE;
        }
        if ((this.equals(ZERO) && another.equals(NEGATIVE)) || (this.equals(NEGATIVE) && another.equals(ZERO))) {
            return NON_POSITIVE;
        }
        if ((this.equals(POSITIVE) && another.equals(NEGATIVE)) || (this.equals(NEGATIVE) && another.equals(POSITIVE))) {
            return NOT_ZERO;
        }
        if ((this.equals(NEGATIVE) && another.equals(NON_POSITIVE)) || (this.equals(NON_POSITIVE) && another.equals(NEGATIVE))) {
            return NON_POSITIVE;
        }
        if ((this.equals(POSITIVE) && another.equals(NON_NEGATIVE)) || (this.equals(NON_NEGATIVE) && another.equals(POSITIVE))) {
            return NON_NEGATIVE;
        }
        if ((this.equals(NON_POSITIVE) && another.equals(NON_NEGATIVE)) || (this.equals(NON_NEGATIVE) && another.equals(NON_POSITIVE))) {
            return TOP;
        }
        if ((this.equals(NON_POSITIVE) && another.equals(POSITIVE)) || (this.equals(POSITIVE) && another.equals(NON_POSITIVE))) {
            return TOP;
        }
        if ((this.equals(NON_NEGATIVE) && another.equals(NEGATIVE)) || (this.equals(NEGATIVE) && another.equals(NON_NEGATIVE))) {
            return TOP;
        }
        if ((this.equals(NON_POSITIVE) && another.equals(ZERO)) || (this.equals(ZERO) && another.equals(NON_POSITIVE))) {
            return NON_POSITIVE;
        }
        if ((this.equals(NON_NEGATIVE) && another.equals(ZERO)) || (this.equals(ZERO) && another.equals(NON_NEGATIVE))) {
            return NON_NEGATIVE;
        }
        if ((this.equals(NOT_ZERO) && another.equals(ZERO)) || (this.equals(ZERO) && another.equals(NOT_ZERO))) {
            return TOP;
        }
        if ((this.equals(NOT_ZERO) && another.equals(POSITIVE)) || (this.equals(POSITIVE) && another.equals(NOT_ZERO))) {
            return NOT_ZERO;
        }
        if ((this.equals(NOT_ZERO) && another.equals(NEGATIVE)) || (this.equals(NEGATIVE) && another.equals(NOT_ZERO))) {
            return NOT_ZERO;
        }
        if ((this.equals(NOT_ZERO) && another.equals(NON_NEGATIVE)) || (this.equals(NON_NEGATIVE) && another.equals(NOT_ZERO))) {
            return TOP;
        }
        if ((this.equals(NOT_ZERO) && another.equals(NON_POSITIVE)) || (this.equals(NON_POSITIVE) && another.equals(NOT_ZERO))) {
            return TOP;
        }

        return TOP; // Default case
    }

    public ZeroAbstractValue negate() {
        switch (this) {
            case NEGATIVE:
                return POSITIVE;
            case POSITIVE:
                return NEGATIVE;
            case NON_NEGATIVE:
                return NON_POSITIVE;
            case NON_POSITIVE:
                return NON_NEGATIVE;
            default:
                return this; // BOTTOM, ZERO, NOT_ZERO, TOP remain unchanged
        }
    }

}
