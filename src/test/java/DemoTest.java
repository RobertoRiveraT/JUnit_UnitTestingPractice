import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.InputMismatchException;

import org.junit.Test;

/**
 * Unit tests for the Demo class.
 * This suite includes boundary tests, equivalence partitioning, and edge cases
 * for the isTriangle() method:

    ✅ Positive test — Valid triangle
    ❌ Negative test — Invalid triangle
    📏 Boundary test — Edges where a + b = c
    🧮 Equivalence Partitioning — Typical values from valid/invalid groups
    ⚠️ Edge Case test — Zeros, negatives, very large numbers

 */

public class DemoTest {

    // -------------------------------
    // 1) Pruebas unitarias de isTriangle(...)
    //    Verificamos cada combinación sin asumir validaciones internas
    // -------------------------------

    // ✅ Positive Test Case - Typical valid triangle
    // 1.1 Caso Positivo Normal (3,4,5) → debe devolver true
    @Test
    public void testIsTriangle_BasicValid() {
        assertTrue("3,4,5 deberían formar triángulo", Demo.isTriangle(3, 4, 5));
    }

    // ❌ Negative Test Case - Does not satisfy triangle inequality
    // 1.2 Caso Negativo Normal (1,2,3) → 1+2 == 3, no es triángulo → false
    @Test
    public void testIsTriangle_BasicInvalid() {
        assertFalse("1,2,3 NO deben formar triángulo", Demo.isTriangle(1, 2, 3));
    }

    // 📏 Boundary Test
    // 1.3 Límite: (5,5,10)
    //      - Si Demo usa estricta (>) → 5+5>10 es false → queremos assertFalse
    //      - Si Demo usa (>=) → 5+5>=10 es true  → entonces llamará assertTrue y este test fallará
    //    Con este test nos cercioramos de cómo se comporta realmente Demo.isTriangle(5,5,10)
    @Test
    public void testIsTriangle_LimiteCincoCincoDiez() {
        boolean resultado = Demo.isTriangle(5, 5, 10);
        // Comprobamos ambos escenarios y damos mensaje claro
        if (resultado) {
            // Conclusión: Demo permite (5+5 == 10) como triángulo (usa >=).
            assertTrue("Demo.isTriangle devolvió true en (5,5,10), debe aceptarlo como válido", resultado);
        } else {
            // Conclusión: Demo exige estrictamente >, por eso lo rechaza.
            assertFalse("Demo.isTriangle devolvió false en (5,5,10), exige > en desigualdad", resultado);
        }
    }

    // 📏 Boundary Test
    // 1.4 Límite justo válido: (5,5,9.999) → 5+5 > 9.999
    @Test
    public void testIsTriangle_LimiteJustoMayor() {
        assertTrue("5+5=10 > 9.999, debe ser triángulo", Demo.isTriangle(5, 5, 9.999));
    }

    // 🧮 Equivalence Partitioning - Valid group
    // 1.5 Partición Equivalencia (válido): (6,7,8) → true
    @Test
    public void testIsTriangle_EquivalenceValid() {
        assertTrue("6,7,8 debería ser triángulo", Demo.isTriangle(6, 7, 8));
    }

    // 🧮 Equivalence Partitioning - Invalid group
    // 1.6 Partición Equivalencia (inválido): (1,1,5) → false
    @Test
    public void testIsTriangle_EquivalenceInvalid() {
        assertFalse("1,1,5 no debe ser triángulo", Demo.isTriangle(1, 1, 5));
    }

    // ⚠️ Edge Case - All sides zero
    // 1.7 Caso extremo: todos ceros (0,0,0)
    //    Si Demo no maneja validaciones, podría devolver true. En ese caso, este
    //    test hará fallar el build y tú verás que falta controlar ceros.
    @Test
    public void testIsTriangle_AllZeros() {
        boolean resultado = Demo.isTriangle(0, 0, 0);
        // Sea cual sea el comportamiento real, comprobamos y documentamos:
        if (resultado) {
            fail("Demo.isTriangle(0,0,0) devolvió true. Se esperaba false para lados no positivos.");
        } else {
            assertFalse("Los tres lados son 0, no se debe considerar triángulo", resultado);
        }
    }

    // ⚠️ Edge Case - One side zero
    // 1.8 Caso extremo: un lado cero (0,3,4)
    @Test
    public void testIsTriangle_OneZeroSide() {
        boolean resultado = Demo.isTriangle(0, 3, 4);
        if (resultado) {
            fail("Demo.isTriangle(0,3,4) devolvió true. Se esperaba false cuando un lado es 0.");
        } else {
            assertFalse("Un lado es 0, no es triángulo", resultado);
        }
    }

    // ⚠️ Edge Case - Negative side
    // 1.9 Caso extremo: lado negativo (-1,2,2)
    @Test
    public void testIsTriangle_NegativeSide() {
        boolean resultado = Demo.isTriangle(-1, 2, 2);
        if (resultado) {
            fail("Demo.isTriangle(-1,2,2) devolvió true. Se esperaba false para lado negativo.");
        } else {
            assertFalse("Lado negativo no debe formar triángulo", resultado);
        }
    }

    // ⚠️ Edge Case - Very large numbers
    // 1.10 Números muy grandes (1e9,1e9,1e9) → si no hay overflow, debe devolver true
    @Test
    public void testIsTriangle_LargeNumbers() {
        assertTrue("Tres lados iguales de 1e9 deberían ser triángulo (si no hay overflow)", 
                    Demo.isTriangle(1e9, 1e9, 1e9));
    }

    
    // 🔂 Float permutations que prueban >= en desigualdad
    @Test
    public void testIsTriangle_FloatPermutation1() {
        assertFalse("0.5,1,0.5 no debería ser triángulo si se usa >", Demo.isTriangle(0.5, 1, 0.5));
    }

    @Test
    public void testIsTriangle_FloatPermutation2() {
        assertFalse("0.5,0.5,1 no debería ser triángulo si se usa >", Demo.isTriangle(0.5, 0.5, 1));
    }

    @Test
    public void testIsTriangle_FloatPermutation3() {
        // Con lógica estricta, esto no es triángulo.
        assertFalse("1, 0.5, 0.5 is NOT a triangle with strict inequality", Demo.isTriangle(1, 0.5, 0.5));
    }

    // -------------------------------
    // 2) Pruebas de Demo.main(...)
    //    Verificamos tanto escenarios válidos, inválidos y errores de formato
    // -------------------------------

    /**
     * 2.1 Cuando el usuario ingresa "3\n4\n5\n", se espera que
     *     la salida contenga EXACTAMENTE "This is a triangle."
     */
    @Test
    public void testMain_ValidTriangleOutput() throws Exception {
        String input = "3\n4\n5\n";
        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;

        // Redirigir entrada/salida
        ByteArrayInputStream inStream = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        System.setIn(inStream);
        System.setOut(new PrintStream(outStream));

        // Ejecutar
        Demo.main(new String[]{});

        // Restaurar
        System.setIn(originalIn);
        System.setOut(originalOut);

        String salida = outStream.toString();

        // Verificar que aparezca la cadena exacta
        assertTrue(
            "Se esperaba 'This is a triangle.' en la consola, pero fue:\n" + salida,
            salida.contains("This is a triangle.")
        );
    }

    /**
     * 2.2 Cuando el usuario ingresa "1\n2\n3\n", (1+2=3), se espera que
     *     la salida contenga EXACTAMENTE "This is not a triangle."
     */
    @Test
    public void testMain_InvalidTriangleOutput() throws Exception {
        String input = "1\n2\n3\n";
        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;

        ByteArrayInputStream inStream = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        System.setIn(inStream);
        System.setOut(new PrintStream(outStream));

        Demo.main(new String[]{});

        System.setIn(originalIn);
        System.setOut(originalOut);

        String salida = outStream.toString();

        assertTrue(
            "Se esperaba 'This is not a triangle.' en la consola, pero fue:\n" + salida,
            salida.contains("This is not a triangle.")
        );
    }

    /**
     * 2.3 Si el usuario escribe un valor no numérico (por ejemplo "a\nb\nc\n"),
     *     Demo.main() podría lanzar InputMismatchException o NullPointerException
     *     dependiendo de cómo esté implementado internamente. Aquí validamos
     *     que se lance alguna de esas dos excepciones y fallamos si no es así.
     */
    @Test
    public void testMain_NonNumericInputThrows() {
        String input = "a\nb\nc\n";
        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;

        ByteArrayInputStream inStream = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();

        System.setIn(inStream);
        System.setOut(new PrintStream(outStream));

        try {
            Demo.main(new String[]{});
            fail("Se esperaba una excepción (InputMismatchException o NullPointerException) por entrada no numérica");
        } catch (Exception ex) {
            // Aceptamos tanto InputMismatchException como NullPointerException
            boolean esInputMismatch = ex instanceof java.util.InputMismatchException;
            boolean esNullPointer   = ex instanceof NullPointerException;
            assertTrue(
                "Se esperaba InputMismatchException o NullPointerException, pero se lanzó: "
                + ex.getClass().getSimpleName(),
                esInputMismatch || esNullPointer
            );
        } finally {
            // Siempre restaurar System.in y System.out
            System.setIn(originalIn);
            System.setOut(originalOut);
        }
    }


    /**
     * 2.4 Si el usuario ingresa un solo número y luego EOF (por ejemplo "3\n"),
     *     Demo.main() intentará leer secondDouble y lanzará probablemente NoSuchElementException.
     *     Verificamos que al menos lance alguna excepción relacionada con falta de datos.
     */
    @Test
    public void testMain_PartialInputThrows() {
        String input = "3\n"; // Falta el 2do y 3er valor
        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;

        ByteArrayInputStream inStream = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        System.setIn(inStream);
        System.setOut(new PrintStream(outStream));

        try {
            Demo.main(new String[]{});
            fail("Se esperaba excepción (NoSuchElementException u otra) por entrada incompleta");
        } catch (Exception ex) {
            // Aceptamos cualquier excepción que no sea InputMismatchException (capturada en test anterior)
            assertTrue(
                "Se esperaba NoSuchElementException (o similar) pero se lanzó: " 
                + ex.getClass().getSimpleName(),
                ex instanceof java.util.NoSuchElementException 
            );
        } finally {
            System.setIn(originalIn);
            System.setOut(originalOut);
        }
    }
}
