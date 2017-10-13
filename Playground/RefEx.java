public class RefEx {

        public int field;

        RefEx (int i) {
            field = i;
        }

        public void transferFrom (RefEx other, int j) {
            field += other.field;
            other.field = j;
            j = 0; // [?] Does this do anything?
        }

        public static void main (String[] args) {
            int j = 5;
            RefEx a = new RefEx(1);
            RefEx b = new RefEx(2);
            a.transferFrom(b, j);

            // [?] What gets printed below?
            System.out.println(a.field);
            System.out.println(b.field);
            System.out.println(j);
        }

  }
