package io.gearstack.enums;

/**
 * GearStackType
 *
 * Indicates the type of GearStack
 *
 * Possible values:
 *  CURRENT
 *  OLD
 *  DREAM
 */
public enum GearStackType {
    CURRENT {
        @Override
        public String toString() {
            return "CURRENT";
        }
    },

    OLD {
        @Override
        public String toString() {
            return "OLD";
        }
    },

    DREAM {
        @Override
        public String toString() {
            return "DREAM";
        }
    }
}
