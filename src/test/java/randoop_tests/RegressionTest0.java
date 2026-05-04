package randoop_tests;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RegressionTest0 {

    public static boolean debug = false;

    public void assertBooleanArrayEquals(boolean[] expectedArray, boolean[] actualArray) {
        if (expectedArray.length != actualArray.length) {
            throw new AssertionError("Array lengths differ: " + expectedArray.length + " != " + actualArray.length);
        }
        for (int i = 0; i < expectedArray.length; i++) {
            if (expectedArray[i] != actualArray[i]) {
                throw new AssertionError("Arrays differ at index " + i + ": " + expectedArray[i] + " != " + actualArray[i]);
            }
        }
    }

    @Test
    public void test001() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test001");
        model.enums.UserType userType0 = model.enums.UserType.GUEST;
        org.junit.Assert.assertTrue("'" + userType0 + "' != '" + model.enums.UserType.GUEST + "'", userType0.equals(model.enums.UserType.GUEST));
    }

    @Test
    public void test002() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test002");
        model.database.ReservationTable.resetToNull();
    }

    @Test
    public void test003() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test003");
        model.enums.UserType userType0 = model.enums.UserType.FACULTY;
        org.junit.Assert.assertTrue("'" + userType0 + "' != '" + model.enums.UserType.FACULTY + "'", userType0.equals(model.enums.UserType.FACULTY));
    }

    @Test
    public void test004() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test004");
        model.enums.EquipmentStatus equipmentStatus0 = model.enums.EquipmentStatus.DISABLED;
        org.junit.Assert.assertTrue("'" + equipmentStatus0 + "' != '" + model.enums.EquipmentStatus.DISABLED + "'", equipmentStatus0.equals(model.enums.EquipmentStatus.DISABLED));
    }

    @Test
    public void test005() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test005");
        model.database.EquipmentTable.resetToNull();
    }

    @Test
    public void test006() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test006");
        model.userhierarchy.Student student5 = new model.userhierarchy.Student((int) '4', "", "hi!", "", (-1));
    }

    @Test
    public void test007() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test007");
        model.userhierarchy.HeadLabCoordinator headLabCoordinator0 = model.userhierarchy.HeadLabCoordinator.getInstance();
        java.lang.String str1 = headLabCoordinator0.getPassword();
        int int2 = headLabCoordinator0.getHourlyRate();
        java.lang.String str3 = headLabCoordinator0.getUsername();
        org.junit.Assert.assertNotNull(headLabCoordinator0);
        org.junit.Assert.assertNull(str1);
        org.junit.Assert.assertTrue("'" + int2 + "' != '" + 0 + "'", int2 == 0);
        org.junit.Assert.assertNull(str3);
    }

    @Test
    public void test008() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test008");
        model.userhierarchy.Faculty faculty5 = new model.userhierarchy.Faculty((int) (byte) 10, "", "hi!", "hi!", 0);
        int int6 = faculty5.getHourlyRate();
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 15 + "'", int6 == 15);
    }

    @Test
    public void test009() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test009");
        model.userhierarchy.HeadLabCoordinator headLabCoordinator0 = model.userhierarchy.HeadLabCoordinator.getInstance();
        headLabCoordinator0.setIsDepartmentApproved(false);
        org.junit.Assert.assertNotNull(headLabCoordinator0);
    }

    @Test
    public void test010() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test010");
        model.database.UserTable.resetToNull();
    }

    @Test
    public void test011() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test011");
        model.IdGeneration.resetToNull();
    }

    @Test
    public void test012() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test012");
        java.time.LocalDateTime localDateTime1 = null;
        java.time.LocalDateTime localDateTime2 = null;
        model.Reservation reservation5 = new model.Reservation(0, localDateTime1, localDateTime2, 2, 10);
        java.lang.Class<?> wildcardClass6 = reservation5.getClass();
        org.junit.Assert.assertNotNull(wildcardClass6);
    }

    @Test
    public void test013() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test013");
        model.database.DatabaseService.resetToNull();
    }

    @Test
    public void test014() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test014");
        model.enums.ReservationStatus reservationStatus0 = model.enums.ReservationStatus.ARRIVED;
        org.junit.Assert.assertTrue("'" + reservationStatus0 + "' != '" + model.enums.ReservationStatus.ARRIVED + "'", reservationStatus0.equals(model.enums.ReservationStatus.ARRIVED));
    }

    @Test
    public void test015() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test015");
        model.enums.ReservationStatus reservationStatus0 = model.enums.ReservationStatus.ACTIVE;
        org.junit.Assert.assertTrue("'" + reservationStatus0 + "' != '" + model.enums.ReservationStatus.ACTIVE + "'", reservationStatus0.equals(model.enums.ReservationStatus.ACTIVE));
    }

    @Test
    public void test016() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test016");
        model.exceptions.EmailNotUniqueException emailNotUniqueException1 = new model.exceptions.EmailNotUniqueException("");
        java.lang.String str2 = emailNotUniqueException1.toString();
        org.junit.Assert.assertEquals("'" + str2 + "' != '" + "model.exceptions.EmailNotUniqueException: " + "'", str2, "model.exceptions.EmailNotUniqueException: ");
    }

    @Test
    public void test017() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test017");
        model.IdGeneration idGeneration0 = model.IdGeneration.getInstance();
        int int1 = idGeneration0.nextEquipmentId();
        org.junit.Assert.assertNotNull(idGeneration0);
// flaky "1) test017(RegressionTest0)":         org.junit.Assert.assertTrue("'" + int1 + "' != '" + 11 + "'", int1 == 11);
    }

    @Test
    public void test018() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test018");
        model.userhierarchy.HeadLabCoordinator headLabCoordinator0 = model.userhierarchy.HeadLabCoordinator.getInstance();
        java.lang.String str1 = headLabCoordinator0.getPassword();
        int int2 = headLabCoordinator0.getHourlyRate();
        model.enums.UserType userType3 = headLabCoordinator0.getUserType();
        boolean boolean4 = headLabCoordinator0.getUniversityAfilliated();
        org.junit.Assert.assertNotNull(headLabCoordinator0);
        org.junit.Assert.assertNull(str1);
        org.junit.Assert.assertTrue("'" + int2 + "' != '" + 0 + "'", int2 == 0);
        org.junit.Assert.assertNull(userType3);
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + true + "'", boolean4 == true);
    }

    @Test
    public void test019() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test019");
        model.Equipment equipment0 = null;
        model.Sensor sensor1 = new model.Sensor(equipment0);
    }

    @Test
    public void test020() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test020");
        model.userhierarchy.Researcher researcher5 = new model.userhierarchy.Researcher((int) (short) 0, "hi!", "model.exceptions.EmailNotUniqueException: ", "", 10);
        int int6 = researcher5.getHourlyRate();
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 20 + "'", int6 == 20);
    }

    @Test
    public void test021() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test021");
        model.IdGeneration idGeneration0 = model.IdGeneration.getInstance();
        int int1 = idGeneration0.nextUserId();
        int int2 = idGeneration0.nextReservationId();
        idGeneration0.setEquipmentIdCount((int) (byte) 0);
        int int5 = idGeneration0.nextUserId();
        int int6 = idGeneration0.nextReservationId();
        org.junit.Assert.assertNotNull(idGeneration0);
// flaky "2) test021(RegressionTest0)":         org.junit.Assert.assertTrue("'" + int1 + "' != '" + 4 + "'", int1 == 4);
// flaky "1) test021(RegressionTest0)":         org.junit.Assert.assertTrue("'" + int2 + "' != '" + 5 + "'", int2 == 5);
// flaky "1) test021(RegressionTest0)":         org.junit.Assert.assertTrue("'" + int5 + "' != '" + 5 + "'", int5 == 5);
// flaky "1) test021(RegressionTest0)":         org.junit.Assert.assertTrue("'" + int6 + "' != '" + 6 + "'", int6 == 6);
    }

    @Test
    public void test022() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test022");
        model.Equipment equipment4 = new model.Equipment((int) (short) 1, "hi!", "hi!", "");
        equipment4.setName("");
        model.enums.EquipmentStatus equipmentStatus7 = model.enums.EquipmentStatus.ENABLED;
        equipment4.setStatus(equipmentStatus7);
        model.Sensor sensor9 = new model.Sensor(equipment4);
        model.enums.EquipmentStatus equipmentStatus10 = model.enums.EquipmentStatus.ENABLED;
        equipment4.setStatus(equipmentStatus10);
        java.lang.String str12 = equipment4.getName();
        org.junit.Assert.assertTrue("'" + equipmentStatus7 + "' != '" + model.enums.EquipmentStatus.ENABLED + "'", equipmentStatus7.equals(model.enums.EquipmentStatus.ENABLED));
        org.junit.Assert.assertTrue("'" + equipmentStatus10 + "' != '" + model.enums.EquipmentStatus.ENABLED + "'", equipmentStatus10.equals(model.enums.EquipmentStatus.ENABLED));
        org.junit.Assert.assertEquals("'" + str12 + "' != '" + "" + "'", str12, "");
    }

    @Test
    public void test023() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test023");
        model.userhierarchy.HeadLabCoordinator headLabCoordinator0 = model.userhierarchy.HeadLabCoordinator.getInstance();
        boolean boolean1 = headLabCoordinator0.getIsDepartmentApproved();
        boolean boolean2 = headLabCoordinator0.getIsDepartmentApproved();
        headLabCoordinator0.setUserId((int) (byte) 1);
        int int5 = headLabCoordinator0.getHourlyRate();
        org.junit.Assert.assertNotNull(headLabCoordinator0);
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + false + "'", boolean1 == false);
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertTrue("'" + int5 + "' != '" + 0 + "'", int5 == 0);
    }

    @Test
    public void test024() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test024");
        model.Equipment equipment4 = new model.Equipment((int) (short) 1, "hi!", "hi!", "");
        java.lang.String str5 = equipment4.getLabLocation();
        java.lang.String str6 = equipment4.getLabLocation();
        model.Equipment equipment11 = new model.Equipment((int) (short) 1, "hi!", "hi!", "");
        equipment11.setName("");
        model.enums.EquipmentStatus equipmentStatus14 = model.enums.EquipmentStatus.ENABLED;
        equipment11.setStatus(equipmentStatus14);
        equipment4.setStatus(equipmentStatus14);
        org.junit.Assert.assertEquals("'" + str5 + "' != '" + "" + "'", str5, "");
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "" + "'", str6, "");
        org.junit.Assert.assertTrue("'" + equipmentStatus14 + "' != '" + model.enums.EquipmentStatus.ENABLED + "'", equipmentStatus14.equals(model.enums.EquipmentStatus.ENABLED));
    }

    @Test
    public void test025() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test025");
        model.exceptions.WeakPasswordException weakPasswordException1 = new model.exceptions.WeakPasswordException("hi!");
        java.lang.String str2 = weakPasswordException1.toString();
        org.junit.Assert.assertEquals("'" + str2 + "' != '" + "model.exceptions.WeakPasswordException: hi!" + "'", str2, "model.exceptions.WeakPasswordException: hi!");
    }

    @Test
    public void test026() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test026");
        java.time.LocalDateTime localDateTime1 = null;
        java.time.LocalDateTime localDateTime2 = null;
        model.Reservation reservation5 = new model.Reservation(4, localDateTime1, localDateTime2, (int) (byte) 0, (int) (short) 0);
    }

    @Test
    public void test027() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test027");
        model.Equipment equipment4 = new model.Equipment((int) (short) 1, "hi!", "hi!", "");
        model.Sensor sensor5 = new model.Sensor(equipment4);
        model.enums.EquipmentStatus equipmentStatus6 = equipment4.getStatus();
        model.Sensor sensor7 = new model.Sensor(equipment4);
        model.enums.EquipmentStatus equipmentStatus8 = model.enums.EquipmentStatus.ENABLED;
        equipment4.setStatus(equipmentStatus8);
        model.Equipment equipment14 = new model.Equipment((int) (short) 1, "hi!", "hi!", "");
        equipment14.setName("");
        model.enums.EquipmentStatus equipmentStatus17 = model.enums.EquipmentStatus.ENABLED;
        equipment14.setStatus(equipmentStatus17);
        model.Sensor sensor19 = new model.Sensor(equipment14);
        equipment4.update(sensor19, "hi!");
        sensor19.departure();
        org.junit.Assert.assertTrue("'" + equipmentStatus6 + "' != '" + model.enums.EquipmentStatus.ENABLED + "'", equipmentStatus6.equals(model.enums.EquipmentStatus.ENABLED));
        org.junit.Assert.assertTrue("'" + equipmentStatus8 + "' != '" + model.enums.EquipmentStatus.ENABLED + "'", equipmentStatus8.equals(model.enums.EquipmentStatus.ENABLED));
        org.junit.Assert.assertTrue("'" + equipmentStatus17 + "' != '" + model.enums.EquipmentStatus.ENABLED + "'", equipmentStatus17.equals(model.enums.EquipmentStatus.ENABLED));
    }

    @Test
    public void test028() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test028");
        model.UserFactory userFactory0 = new model.UserFactory();
        model.enums.UserType userType5 = null;
        // The following exception was thrown during execution in test generation
        try {
            model.userhierarchy.User user6 = userFactory0.createUser("model.exceptions.EmailNotUniqueException: ", "model.exceptions.WeakPasswordException: hi!", "model.exceptions.WeakPasswordException: hi!", (int) (byte) 1, userType5);
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Cannot invoke \"model.enums.UserType.ordinal()\" because \"type\" is null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
    }

    @Test
    public void test029() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test029");
        model.userhierarchy.LabManager labManager5 = new model.userhierarchy.LabManager(4, "model.exceptions.WeakPasswordException: hi!", "model.exceptions.EmailNotUniqueException: ", "hi!", (int) (short) 1);
        int int6 = labManager5.getHourlyRate();
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 0 + "'", int6 == 0);
    }

    @Test
    public void test030() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test030");
        java.time.LocalDateTime localDateTime1 = null;
        java.time.LocalDateTime localDateTime2 = null;
        model.Reservation reservation5 = new model.Reservation(0, localDateTime1, localDateTime2, 2, 10);
        reservation5.setId((int) (short) 1);
        java.time.LocalDateTime localDateTime8 = reservation5.getStartTime();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str9 = reservation5.toString();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Cannot invoke \"java.time.LocalDateTime.toString()\" because \"this.startTime\" is null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(localDateTime8);
    }

    @Test
    public void test031() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test031");
        model.userhierarchy.HeadLabCoordinator headLabCoordinator0 = model.userhierarchy.HeadLabCoordinator.getInstance();
        java.lang.String str1 = headLabCoordinator0.getPassword();
        int int2 = headLabCoordinator0.getId();
        java.lang.String str3 = headLabCoordinator0.getEmail();
        java.lang.String str4 = headLabCoordinator0.getUsername();
        org.junit.Assert.assertNotNull(headLabCoordinator0);
// flaky "3) test031(RegressionTest0)":         org.junit.Assert.assertEquals("'" + str1 + "' != '" + "" + "'", str1, "");
        org.junit.Assert.assertTrue("'" + int2 + "' != '" + 1 + "'", int2 == 1);
// flaky "2) test031(RegressionTest0)":         org.junit.Assert.assertEquals("'" + str3 + "' != '" + "model.exceptions.EmailNotUniqueException: " + "'", str3, "model.exceptions.EmailNotUniqueException: ");
// flaky "2) test031(RegressionTest0)":         org.junit.Assert.assertEquals("'" + str4 + "' != '" + "" + "'", str4, "");
    }

    @Test
    public void test032() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test032");
        model.Equipment equipment4 = new model.Equipment((int) (short) -1, "hi!", "model.exceptions.EmailNotUniqueException: ", "");
    }

    @Test
    public void test033() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test033");
        model.userhierarchy.Guest guest5 = new model.userhierarchy.Guest(10, "", "model.exceptions.WeakPasswordException: hi!", "model.exceptions.WeakPasswordException: hi!", 15);
    }

    @Test
    public void test034() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test034");
        model.Equipment equipment4 = new model.Equipment((int) (short) 1, "hi!", "hi!", "");
        model.Sensor sensor5 = new model.Sensor(equipment4);
        model.enums.EquipmentStatus equipmentStatus6 = equipment4.getStatus();
        model.Sensor sensor7 = new model.Sensor(equipment4);
        java.lang.String str8 = equipment4.getLabLocation();
        org.junit.Assert.assertTrue("'" + equipmentStatus6 + "' != '" + model.enums.EquipmentStatus.ENABLED + "'", equipmentStatus6.equals(model.enums.EquipmentStatus.ENABLED));
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "" + "'", str8, "");
    }

    @Test
    public void test035() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test035");
        java.time.LocalDateTime localDateTime1 = null;
        java.time.LocalDateTime localDateTime2 = null;
        model.Reservation reservation5 = new model.Reservation(0, localDateTime1, localDateTime2, 2, 10);
        reservation5.setId((int) (short) 1);
        java.time.LocalDateTime localDateTime8 = reservation5.getStartTime();
        reservation5.setUserId((int) 'a');
        java.time.LocalDateTime localDateTime11 = reservation5.getStartTime();
        reservation5.cancelReservation();
        org.junit.Assert.assertNull(localDateTime8);
        org.junit.Assert.assertNull(localDateTime11);
    }

    @Test
    public void test036() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test036");
        model.exceptions.EmailNotUniqueException emailNotUniqueException1 = new model.exceptions.EmailNotUniqueException("hi!");
    }

    @Test
    public void test037() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test037");
        java.time.LocalDateTime localDateTime1 = null;
        java.time.LocalDateTime localDateTime2 = null;
        model.Reservation reservation5 = new model.Reservation(0, localDateTime1, localDateTime2, 2, 10);
        reservation5.setId((int) (short) 1);
        java.time.LocalDateTime localDateTime8 = reservation5.getStartTime();
        int int9 = reservation5.getEquipmentId();
        int int10 = reservation5.getDepositAmount();
        java.time.LocalDateTime localDateTime11 = reservation5.getStartTime();
        org.junit.Assert.assertNull(localDateTime8);
        org.junit.Assert.assertTrue("'" + int9 + "' != '" + 10 + "'", int9 == 10);
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 0 + "'", int10 == 0);
        org.junit.Assert.assertNull(localDateTime11);
    }

    @Test
    public void test038() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test038");
        model.enums.UserType userType0 = model.enums.UserType.STUDENT;
        org.junit.Assert.assertTrue("'" + userType0 + "' != '" + model.enums.UserType.STUDENT + "'", userType0.equals(model.enums.UserType.STUDENT));
    }

    @Test
    public void test039() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test039");
        model.exceptions.WeakPasswordException weakPasswordException1 = new model.exceptions.WeakPasswordException("model.exceptions.WeakPasswordException: hi!");
        java.lang.String str2 = weakPasswordException1.toString();
        org.junit.Assert.assertEquals("'" + str2 + "' != '" + "model.exceptions.WeakPasswordException: model.exceptions.WeakPasswordException: hi!" + "'", str2, "model.exceptions.WeakPasswordException: model.exceptions.WeakPasswordException: hi!");
    }

    @Test
    public void test040() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test040");
        java.time.LocalDateTime localDateTime1 = null;
        java.time.LocalDateTime localDateTime2 = null;
        model.Reservation reservation5 = new model.Reservation(0, localDateTime1, localDateTime2, 2, 10);
        int int6 = reservation5.getTotalOwed();
        java.time.LocalDateTime localDateTime7 = reservation5.getEndTime();
        reservation5.setId((int) 'a');
        reservation5.cancelReservation();
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 0 + "'", int6 == 0);
        org.junit.Assert.assertNull(localDateTime7);
    }

    @Test
    public void test041() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test041");
        model.userhierarchy.Student student5 = new model.userhierarchy.Student((int) (short) 0, "hi!", "hi!", "hi!", 0);
        int int6 = student5.getHourlyRate();
        int int7 = student5.getHourlyRate();
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 10 + "'", int6 == 10);
        org.junit.Assert.assertTrue("'" + int7 + "' != '" + 10 + "'", int7 == 10);
    }

    @Test
    public void test042() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test042");
        model.Equipment equipment4 = new model.Equipment((int) (short) 1, "hi!", "hi!", "");
        java.lang.String str5 = equipment4.getLabLocation();
        model.Sensor sensor6 = equipment4.getSensor();
        sensor6.notifyObservers("");
        model.Equipment equipment13 = new model.Equipment((int) (short) 1, "hi!", "hi!", "");
        java.lang.String str14 = equipment13.getLabLocation();
        model.Sensor sensor15 = equipment13.getSensor();
        sensor6.detach((model.SensorObserver) equipment13);
        equipment13.setDescription("model.exceptions.EmailNotUniqueException: ");
        java.lang.String str19 = equipment13.getName();
        org.junit.Assert.assertEquals("'" + str5 + "' != '" + "" + "'", str5, "");
        org.junit.Assert.assertNotNull(sensor6);
        org.junit.Assert.assertEquals("'" + str14 + "' != '" + "" + "'", str14, "");
        org.junit.Assert.assertNotNull(sensor15);
        org.junit.Assert.assertEquals("'" + str19 + "' != '" + "hi!" + "'", str19, "hi!");
    }

    @Test
    public void test043() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test043");
        model.Equipment equipment4 = new model.Equipment((int) (byte) 0, "model.exceptions.WeakPasswordException: model.exceptions.WeakPasswordException: hi!", "model.exceptions.WeakPasswordException: model.exceptions.WeakPasswordException: hi!", "model.exceptions.WeakPasswordException: hi!");
    }

    @Test
    public void test044() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test044");
        model.userhierarchy.HeadLabCoordinator headLabCoordinator0 = model.userhierarchy.HeadLabCoordinator.getInstance();
        boolean boolean1 = headLabCoordinator0.getIsDepartmentApproved();
        boolean boolean2 = headLabCoordinator0.getIsDepartmentApproved();
        headLabCoordinator0.setUserId((int) (byte) 1);
        int int5 = headLabCoordinator0.getUserId();
        int int6 = headLabCoordinator0.getHourlyRate();
        int int7 = headLabCoordinator0.getHourlyRate();
        org.junit.Assert.assertNotNull(headLabCoordinator0);
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + false + "'", boolean1 == false);
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertTrue("'" + int5 + "' != '" + 1 + "'", int5 == 1);
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 0 + "'", int6 == 0);
        org.junit.Assert.assertTrue("'" + int7 + "' != '" + 0 + "'", int7 == 0);
    }

    @Test
    public void test045() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test045");
        java.time.LocalDateTime localDateTime1 = null;
        java.time.LocalDateTime localDateTime2 = null;
        model.Reservation reservation5 = new model.Reservation(0, localDateTime1, localDateTime2, 2, 10);
        int int6 = reservation5.getTotalOwed();
        java.time.LocalDateTime localDateTime7 = reservation5.getEndTime();
        int int8 = reservation5.getEquipmentId();
        int int9 = reservation5.getUserId();
        reservation5.setTotalOwed((int) (byte) 10);
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 0 + "'", int6 == 0);
        org.junit.Assert.assertNull(localDateTime7);
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 10 + "'", int8 == 10);
        org.junit.Assert.assertTrue("'" + int9 + "' != '" + 2 + "'", int9 == 2);
    }

    @Test
    public void test046() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test046");
        model.paymentclasses.CreditPayment creditPayment0 = new model.paymentclasses.CreditPayment();
        creditPayment0.makePayment(20);
    }

    @Test
    public void test047() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test047");
        model.Equipment equipment4 = new model.Equipment((int) (short) 1, "hi!", "hi!", "");
        equipment4.setName("");
        equipment4.setDescription("hi!");
        equipment4.setDescription("");
        int int11 = equipment4.getId();
        org.junit.Assert.assertTrue("'" + int11 + "' != '" + 1 + "'", int11 == 1);
    }

    @Test
    public void test048() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test048");
        java.time.LocalDateTime localDateTime1 = null;
        java.time.LocalDateTime localDateTime2 = null;
        model.Reservation reservation5 = new model.Reservation(0, localDateTime1, localDateTime2, 2, 10);
        reservation5.setId((int) (short) 1);
        java.time.LocalDateTime localDateTime8 = reservation5.getStartTime();
        reservation5.setEquipmentId((int) ' ');
        java.time.LocalDateTime localDateTime11 = null;
        reservation5.setEndTime(localDateTime11);
        model.enums.ReservationStatus reservationStatus13 = reservation5.getStatus();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str14 = reservation5.toString();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Cannot invoke \"java.time.LocalDateTime.toString()\" because \"this.startTime\" is null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(localDateTime8);
        org.junit.Assert.assertNull(reservationStatus13);
    }

    @Test
    public void test049() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test049");
        model.IdGeneration idGeneration0 = model.IdGeneration.getInstance();
        int int1 = idGeneration0.nextUserId();
        idGeneration0.setEquipmentIdCount((int) (short) 10);
        int int4 = idGeneration0.nextReservationId();
        java.lang.Class<?> wildcardClass5 = idGeneration0.getClass();
        org.junit.Assert.assertNotNull(idGeneration0);
// flaky "4) test049(RegressionTest0)":         org.junit.Assert.assertTrue("'" + int1 + "' != '" + 33 + "'", int1 == 33);
// flaky "3) test049(RegressionTest0)":         org.junit.Assert.assertTrue("'" + int4 + "' != '" + 7 + "'", int4 == 7);
        org.junit.Assert.assertNotNull(wildcardClass5);
    }

    @Test
    public void test050() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test050");
        java.time.LocalDateTime localDateTime1 = null;
        java.time.LocalDateTime localDateTime2 = null;
        model.Reservation reservation5 = new model.Reservation(0, localDateTime1, localDateTime2, 2, 10);
        reservation5.setId((int) (short) 1);
        java.time.LocalDateTime localDateTime8 = reservation5.getStartTime();
        reservation5.setEquipmentId((int) ' ');
        java.time.LocalDateTime localDateTime11 = null;
        reservation5.setStartTime(localDateTime11);
        java.time.LocalDateTime localDateTime13 = null;
        reservation5.setEndTime(localDateTime13);
        org.junit.Assert.assertNull(localDateTime8);
    }

    @Test
    public void test051() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test051");
        model.userhierarchy.Student student5 = new model.userhierarchy.Student((-1), "", "model.exceptions.WeakPasswordException: hi!", "", (int) ' ');
    }

    @Test
    public void test052() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test052");
        model.userhierarchy.HeadLabCoordinator headLabCoordinator0 = model.userhierarchy.HeadLabCoordinator.getInstance();
        java.lang.String str1 = headLabCoordinator0.getPassword();
        int int2 = headLabCoordinator0.getId();
        java.lang.String str3 = headLabCoordinator0.getEmail();
        int int4 = headLabCoordinator0.getUserId();
        org.junit.Assert.assertNotNull(headLabCoordinator0);
// flaky "5) test052(RegressionTest0)":         org.junit.Assert.assertEquals("'" + str1 + "' != '" + "" + "'", str1, "");
        org.junit.Assert.assertTrue("'" + int2 + "' != '" + 1 + "'", int2 == 1);
// flaky "4) test052(RegressionTest0)":         org.junit.Assert.assertEquals("'" + str3 + "' != '" + "model.exceptions.EmailNotUniqueException: " + "'", str3, "model.exceptions.EmailNotUniqueException: ");
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 1 + "'", int4 == 1);
    }

    @Test
    public void test053() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test053");
        model.Equipment equipment4 = new model.Equipment((int) (short) 1, "hi!", "hi!", "");
        java.lang.String str5 = equipment4.getLabLocation();
        model.Sensor sensor6 = equipment4.getSensor();
        sensor6.notifyObservers("");
        model.Equipment equipment13 = new model.Equipment((int) (short) 1, "hi!", "hi!", "");
        java.lang.String str14 = equipment13.getLabLocation();
        model.Sensor sensor15 = equipment13.getSensor();
        sensor6.detach((model.SensorObserver) equipment13);
        equipment13.setDescription("model.exceptions.EmailNotUniqueException: ");
        java.lang.String str19 = equipment13.toString();
        org.junit.Assert.assertEquals("'" + str5 + "' != '" + "" + "'", str5, "");
        org.junit.Assert.assertNotNull(sensor6);
        org.junit.Assert.assertEquals("'" + str14 + "' != '" + "" + "'", str14, "");
        org.junit.Assert.assertNotNull(sensor15);
        org.junit.Assert.assertEquals("'" + str19 + "' != '" + "1 hi! model.exceptions.EmailNotUniqueException:   ENABLED" + "'", str19, "1 hi! model.exceptions.EmailNotUniqueException:   ENABLED");
    }

    @Test
    public void test054() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test054");
        model.userhierarchy.HeadLabCoordinator headLabCoordinator0 = model.userhierarchy.HeadLabCoordinator.getInstance();
        java.lang.String str1 = headLabCoordinator0.getPassword();
        int int2 = headLabCoordinator0.getHourlyRate();
        int int3 = headLabCoordinator0.getHourlyRate();
        headLabCoordinator0.setUniversityAfilliated(false);
        org.junit.Assert.assertNotNull(headLabCoordinator0);
// flaky "6) test054(RegressionTest0)":         org.junit.Assert.assertEquals("'" + str1 + "' != '" + "" + "'", str1, "");
        org.junit.Assert.assertTrue("'" + int2 + "' != '" + 0 + "'", int2 == 0);
        org.junit.Assert.assertTrue("'" + int3 + "' != '" + 0 + "'", int3 == 0);
    }

    @Test
    public void test055() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test055");
        java.time.LocalDateTime localDateTime1 = null;
        java.time.LocalDateTime localDateTime2 = null;
        model.Reservation reservation5 = new model.Reservation(0, localDateTime1, localDateTime2, 2, 10);
        reservation5.setId((int) (short) 1);
        java.time.LocalDateTime localDateTime8 = reservation5.getStartTime();
        reservation5.setEquipmentId((int) ' ');
        java.time.LocalDateTime localDateTime11 = null;
        reservation5.setEndTime(localDateTime11);
        model.enums.ReservationStatus reservationStatus13 = reservation5.getStatus();
        reservation5.cancelReservation();
        org.junit.Assert.assertNull(localDateTime8);
        org.junit.Assert.assertNull(reservationStatus13);
    }

    @Test
    public void test056() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test056");
        model.exceptions.WeakPasswordException weakPasswordException1 = new model.exceptions.WeakPasswordException("model.exceptions.WeakPasswordException: model.exceptions.WeakPasswordException: hi!");
    }

    @Test
    public void test057() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test057");
        java.time.LocalDateTime localDateTime1 = null;
        java.time.LocalDateTime localDateTime2 = null;
        model.Reservation reservation5 = new model.Reservation(33, localDateTime1, localDateTime2, 33, 100);
    }

    @Test
    public void test058() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test058");
        model.userhierarchy.Student student5 = new model.userhierarchy.Student((int) (short) 0, "hi!", "hi!", "hi!", 0);
        int int6 = student5.getHourlyRate();
        java.lang.String str7 = student5.getUsername();
        int int8 = student5.getId();
        int int9 = student5.getVerificationNum();
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 10 + "'", int6 == 10);
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + "hi!" + "'", str7, "hi!");
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 0 + "'", int8 == 0);
        org.junit.Assert.assertTrue("'" + int9 + "' != '" + 0 + "'", int9 == 0);
    }

    @Test
    public void test059() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test059");
        java.time.LocalDateTime localDateTime1 = null;
        java.time.LocalDateTime localDateTime2 = null;
        model.Reservation reservation5 = new model.Reservation((int) (byte) -1, localDateTime1, localDateTime2, (int) '#', (int) (short) 0);
        model.enums.ReservationStatus reservationStatus6 = null;
        reservation5.setStatus(reservationStatus6);
    }

    @Test
    public void test060() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test060");
        model.userhierarchy.Faculty faculty5 = new model.userhierarchy.Faculty((int) (short) -1, "", "", "model.exceptions.EmailNotUniqueException: ", (int) (byte) 10);
        int int6 = faculty5.getVerificationNum();
        int int7 = faculty5.getHourlyRate();
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 10 + "'", int6 == 10);
        org.junit.Assert.assertTrue("'" + int7 + "' != '" + 15 + "'", int7 == 15);
    }

    @Test
    public void test061() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test061");
        model.Checkout checkout0 = new model.Checkout();
        model.paymentclasses.CreditPayment creditPayment1 = new model.paymentclasses.CreditPayment();
        creditPayment1.makePayment((int) (short) -1);
        checkout0.setPaymentStrategy((model.paymentclasses.PaymentStrategy) creditPayment1);
    }

    @Test
    public void test062() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test062");
        model.userhierarchy.Faculty faculty5 = new model.userhierarchy.Faculty(15, "1 hi! model.exceptions.EmailNotUniqueException:   ENABLED", "model.exceptions.WeakPasswordException: hi!", "model.exceptions.WeakPasswordException: model.exceptions.WeakPasswordException: hi!", (int) (short) 100);
    }

    @Test
    public void test063() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test063");
        model.userhierarchy.LabManager labManager5 = new model.userhierarchy.LabManager((int) (short) -1, "hi!", "model.exceptions.WeakPasswordException: hi!", "1 hi! model.exceptions.EmailNotUniqueException:   ENABLED", (int) 'a');
    }

    @Test
    public void test064() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test064");
        model.IdGeneration idGeneration0 = model.IdGeneration.getInstance();
        int int1 = idGeneration0.nextUserId();
        idGeneration0.setEquipmentIdCount((int) (short) 10);
        int int4 = idGeneration0.nextReservationId();
        int int5 = idGeneration0.nextReservationId();
        int int6 = idGeneration0.nextEquipmentId();
        int int7 = idGeneration0.nextReservationId();
        org.junit.Assert.assertNotNull(idGeneration0);
// flaky "7) test064(RegressionTest0)":         org.junit.Assert.assertTrue("'" + int1 + "' != '" + 35 + "'", int1 == 35);
// flaky "5) test064(RegressionTest0)":         org.junit.Assert.assertTrue("'" + int4 + "' != '" + 11 + "'", int4 == 11);
// flaky "3) test064(RegressionTest0)":         org.junit.Assert.assertTrue("'" + int5 + "' != '" + 12 + "'", int5 == 12);
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 10 + "'", int6 == 10);
// flaky "2) test064(RegressionTest0)":         org.junit.Assert.assertTrue("'" + int7 + "' != '" + 13 + "'", int7 == 13);
    }

    @Test
    public void test065() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test065");
        java.time.LocalDateTime localDateTime1 = null;
        java.time.LocalDateTime localDateTime2 = null;
        model.Reservation reservation5 = new model.Reservation(0, localDateTime1, localDateTime2, 2, 10);
        reservation5.setId((int) (short) 1);
        java.time.LocalDateTime localDateTime8 = reservation5.getStartTime();
        int int9 = reservation5.getEquipmentId();
        int int10 = reservation5.getDepositAmount();
        int int11 = reservation5.getTotalOwed();
        int int12 = reservation5.getId();
        org.junit.Assert.assertNull(localDateTime8);
        org.junit.Assert.assertTrue("'" + int9 + "' != '" + 10 + "'", int9 == 10);
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 0 + "'", int10 == 0);
        org.junit.Assert.assertTrue("'" + int11 + "' != '" + 0 + "'", int11 == 0);
        org.junit.Assert.assertTrue("'" + int12 + "' != '" + 1 + "'", int12 == 1);
    }

    @Test
    public void test066() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test066");
        model.paymentclasses.DebitPayment debitPayment0 = new model.paymentclasses.DebitPayment();
        debitPayment0.makePayment((int) (short) 1);
        debitPayment0.makePayment(2);
    }

    @Test
    public void test067() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test067");
        model.userhierarchy.Faculty faculty5 = new model.userhierarchy.Faculty((int) (byte) 10, "", "hi!", "hi!", 0);
        int int6 = faculty5.getVerificationNum();
        faculty5.setIsDepartmentApproved(false);
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 0 + "'", int6 == 0);
    }

    @Test
    public void test068() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test068");
        model.IdGeneration idGeneration0 = model.IdGeneration.getInstance();
        idGeneration0.setUserIdCount((int) ' ');
        int int3 = idGeneration0.nextEquipmentId();
        org.junit.Assert.assertNotNull(idGeneration0);
// flaky "8) test068(RegressionTest0)":         org.junit.Assert.assertTrue("'" + int3 + "' != '" + 0 + "'", int3 == 0);
    }

    @Test
    public void test069() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test069");
        java.time.LocalDateTime localDateTime1 = null;
        java.time.LocalDateTime localDateTime2 = null;
        model.Reservation reservation5 = new model.Reservation(0, localDateTime1, localDateTime2, 2, 10);
        reservation5.setId((int) (short) 1);
        java.time.LocalDateTime localDateTime8 = reservation5.getStartTime();
        int int9 = reservation5.getEquipmentId();
        int int10 = reservation5.getUserId();
        int int11 = reservation5.getTotalOwed();
        int int12 = reservation5.getId();
        org.junit.Assert.assertNull(localDateTime8);
        org.junit.Assert.assertTrue("'" + int9 + "' != '" + 10 + "'", int9 == 10);
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 2 + "'", int10 == 2);
        org.junit.Assert.assertTrue("'" + int11 + "' != '" + 0 + "'", int11 == 0);
        org.junit.Assert.assertTrue("'" + int12 + "' != '" + 1 + "'", int12 == 1);
    }

    @Test
    public void test070() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test070");
        model.userhierarchy.LabManager labManager5 = new model.userhierarchy.LabManager((int) '4', "model.exceptions.EmailNotUniqueException: ", "hi!", "hi!", (int) (short) 10);
        int int6 = labManager5.getId();
        int int7 = labManager5.getId();
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 52 + "'", int6 == 52);
        org.junit.Assert.assertTrue("'" + int7 + "' != '" + 52 + "'", int7 == 52);
    }

    @Test
    public void test071() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test071");
        java.time.LocalDateTime localDateTime1 = null;
        java.time.LocalDateTime localDateTime2 = null;
        model.Reservation reservation5 = new model.Reservation(0, localDateTime1, localDateTime2, 2, 10);
        reservation5.setId((int) (short) 1);
        java.time.LocalDateTime localDateTime8 = reservation5.getStartTime();
        int int9 = reservation5.getEquipmentId();
        int int10 = reservation5.getUserId();
        model.Equipment equipment15 = new model.Equipment((int) (short) 1, "hi!", "hi!", "");
        model.Sensor sensor16 = new model.Sensor(equipment15);
        model.enums.EquipmentStatus equipmentStatus17 = equipment15.getStatus();
        model.Sensor sensor18 = new model.Sensor(equipment15);
        reservation5.update(sensor18, "");
        reservation5.cancelReservation();
        java.time.LocalDateTime localDateTime22 = reservation5.getStartTime();
        org.junit.Assert.assertNull(localDateTime8);
        org.junit.Assert.assertTrue("'" + int9 + "' != '" + 10 + "'", int9 == 10);
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 2 + "'", int10 == 2);
        org.junit.Assert.assertTrue("'" + equipmentStatus17 + "' != '" + model.enums.EquipmentStatus.ENABLED + "'", equipmentStatus17.equals(model.enums.EquipmentStatus.ENABLED));
        org.junit.Assert.assertNull(localDateTime22);
    }

    @Test
    public void test072() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test072");
        java.time.LocalDateTime localDateTime1 = null;
        java.time.LocalDateTime localDateTime2 = null;
        model.Reservation reservation5 = new model.Reservation(0, localDateTime1, localDateTime2, 2, 10);
        reservation5.setId((int) (short) 1);
        java.time.LocalDateTime localDateTime8 = reservation5.getStartTime();
        int int9 = reservation5.getEquipmentId();
        java.time.LocalDateTime localDateTime10 = null;
        reservation5.setStartTime(localDateTime10);
        int int12 = reservation5.getId();
        org.junit.Assert.assertNull(localDateTime8);
        org.junit.Assert.assertTrue("'" + int9 + "' != '" + 10 + "'", int9 == 10);
        org.junit.Assert.assertTrue("'" + int12 + "' != '" + 1 + "'", int12 == 1);
    }

    @Test
    public void test073() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test073");
        java.time.LocalDateTime localDateTime1 = null;
        java.time.LocalDateTime localDateTime2 = null;
        model.Reservation reservation5 = new model.Reservation(0, localDateTime1, localDateTime2, 2, 10);
        reservation5.setId((int) (short) 1);
        reservation5.setId((int) 'a');
        java.time.LocalDateTime localDateTime10 = null;
        reservation5.setStartTime(localDateTime10);
        reservation5.cancelReservation();
    }

    @Test
    public void test074() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test074");
        model.userhierarchy.Researcher researcher5 = new model.userhierarchy.Researcher(0, "model.exceptions.WeakPasswordException: hi!", "model.exceptions.EmailNotUniqueException: ", "1 hi! model.exceptions.EmailNotUniqueException:   ENABLED", 20);
    }

    @Test
    public void test075() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test075");
        model.exceptions.EmailNotUniqueException emailNotUniqueException1 = new model.exceptions.EmailNotUniqueException("model.exceptions.EmailNotUniqueException: ");
    }

    @Test
    public void test076() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test076");
        model.userhierarchy.HeadLabCoordinator headLabCoordinator0 = model.userhierarchy.HeadLabCoordinator.getInstance();
        boolean boolean1 = headLabCoordinator0.getIsDepartmentApproved();
        int int2 = headLabCoordinator0.getHourlyRate();
        int int3 = headLabCoordinator0.getHourlyRate();
        headLabCoordinator0.setUniversityAfilliated(false);
        java.lang.String str6 = headLabCoordinator0.getPassword();
        org.junit.Assert.assertNotNull(headLabCoordinator0);
// flaky "9) test076(RegressionTest0)":         org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + true + "'", boolean1 == true);
        org.junit.Assert.assertTrue("'" + int2 + "' != '" + 0 + "'", int2 == 0);
        org.junit.Assert.assertTrue("'" + int3 + "' != '" + 0 + "'", int3 == 0);
// flaky "6) test076(RegressionTest0)":         org.junit.Assert.assertEquals("'" + str6 + "' != '" + "model.exceptions.WeakPasswordException: model.exceptions.WeakPasswordException: hi!" + "'", str6, "model.exceptions.WeakPasswordException: model.exceptions.WeakPasswordException: hi!");
    }

    @Test
    public void test077() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test077");
        java.time.LocalDateTime localDateTime1 = null;
        java.time.LocalDateTime localDateTime2 = null;
        model.Reservation reservation5 = new model.Reservation(0, localDateTime1, localDateTime2, 2, 10);
        reservation5.setId((int) (short) 1);
        java.time.LocalDateTime localDateTime8 = reservation5.getStartTime();
        int int9 = reservation5.getEquipmentId();
        int int10 = reservation5.getUserId();
        int int11 = reservation5.getTotalOwed();
        java.time.LocalDateTime localDateTime12 = reservation5.getEndTime();
        reservation5.setDepositAmount(11);
        int int15 = reservation5.getDepositAmount();
        org.junit.Assert.assertNull(localDateTime8);
        org.junit.Assert.assertTrue("'" + int9 + "' != '" + 10 + "'", int9 == 10);
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 2 + "'", int10 == 2);
        org.junit.Assert.assertTrue("'" + int11 + "' != '" + 0 + "'", int11 == 0);
        org.junit.Assert.assertNull(localDateTime12);
        org.junit.Assert.assertTrue("'" + int15 + "' != '" + 11 + "'", int15 == 11);
    }

    @Test
    public void test078() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test078");
        model.Checkout checkout0 = new model.Checkout();
        model.paymentclasses.PaymentStrategy paymentStrategy1 = null;
        checkout0.setPaymentStrategy(paymentStrategy1);
    }

    @Test
    public void test079() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test079");
        model.userhierarchy.Researcher researcher5 = new model.userhierarchy.Researcher(5, "model.exceptions.WeakPasswordException: hi!", "model.exceptions.WeakPasswordException: hi!", "model.exceptions.WeakPasswordException: model.exceptions.WeakPasswordException: hi!", 15);
    }

    @Test
    public void test080() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test080");
        java.time.LocalDateTime localDateTime1 = null;
        java.time.LocalDateTime localDateTime2 = null;
        model.Reservation reservation5 = new model.Reservation(15, localDateTime1, localDateTime2, 9, (int) (byte) -1);
    }

    @Test
    public void test081() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test081");
        java.time.LocalDateTime localDateTime1 = null;
        java.time.LocalDateTime localDateTime2 = null;
        model.Reservation reservation5 = new model.Reservation(0, localDateTime1, localDateTime2, 2, 10);
        reservation5.setId((int) (short) 1);
        java.time.LocalDateTime localDateTime8 = reservation5.getStartTime();
        int int9 = reservation5.getEquipmentId();
        int int10 = reservation5.getUserId();
        model.Equipment equipment15 = new model.Equipment((int) (short) 1, "hi!", "hi!", "");
        model.Sensor sensor16 = new model.Sensor(equipment15);
        model.enums.EquipmentStatus equipmentStatus17 = equipment15.getStatus();
        model.Sensor sensor18 = new model.Sensor(equipment15);
        reservation5.update(sensor18, "");
        model.Equipment equipment25 = new model.Equipment((int) (short) 1, "hi!", "hi!", "");
        equipment25.setName("");
        equipment25.setDescription("hi!");
        equipment25.setDescription("");
        model.Sensor sensor32 = new model.Sensor(equipment25);
        reservation5.update(sensor32, "model.exceptions.EmailNotUniqueException: ");
        java.time.LocalDateTime localDateTime35 = reservation5.getEndTime();
        org.junit.Assert.assertNull(localDateTime8);
        org.junit.Assert.assertTrue("'" + int9 + "' != '" + 10 + "'", int9 == 10);
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 2 + "'", int10 == 2);
        org.junit.Assert.assertTrue("'" + equipmentStatus17 + "' != '" + model.enums.EquipmentStatus.ENABLED + "'", equipmentStatus17.equals(model.enums.EquipmentStatus.ENABLED));
        org.junit.Assert.assertNull(localDateTime35);
    }

    @Test
    public void test082() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test082");
        model.UserFactory userFactory0 = new model.UserFactory();
        model.enums.UserType userType6 = model.enums.UserType.RESEARCHER;
        model.userhierarchy.User user7 = userFactory0.createUser((int) (byte) 100, "hi!", "model.exceptions.WeakPasswordException: model.exceptions.WeakPasswordException: hi!", "", 100, userType6);
        model.UserFactory userFactory12 = new model.UserFactory();
        model.enums.UserType userType18 = model.enums.UserType.HEADLABCOORDINATOR;
        model.userhierarchy.User user19 = userFactory12.createUser(35, "model.exceptions.WeakPasswordException: model.exceptions.WeakPasswordException: hi!", "hi!", "model.exceptions.WeakPasswordException: model.exceptions.WeakPasswordException: hi!", (int) (short) 10, userType18);
        model.userhierarchy.User user20 = userFactory0.createUser("model.exceptions.WeakPasswordException: model.exceptions.WeakPasswordException: hi!", "model.exceptions.WeakPasswordException: hi!", "hi!", 12, userType18);
        org.junit.Assert.assertTrue("'" + userType6 + "' != '" + model.enums.UserType.RESEARCHER + "'", userType6.equals(model.enums.UserType.RESEARCHER));
        org.junit.Assert.assertNotNull(user7);
        org.junit.Assert.assertTrue("'" + userType18 + "' != '" + model.enums.UserType.HEADLABCOORDINATOR + "'", userType18.equals(model.enums.UserType.HEADLABCOORDINATOR));
        org.junit.Assert.assertNotNull(user19);
        org.junit.Assert.assertNotNull(user20);
    }

    @Test
    public void test083() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test083");
        model.Equipment equipment4 = new model.Equipment((int) (short) 1, "hi!", "hi!", "");
        model.Sensor sensor5 = new model.Sensor(equipment4);
        model.enums.EquipmentStatus equipmentStatus6 = equipment4.getStatus();
        java.lang.Class<?> wildcardClass7 = equipmentStatus6.getClass();
        org.junit.Assert.assertTrue("'" + equipmentStatus6 + "' != '" + model.enums.EquipmentStatus.ENABLED + "'", equipmentStatus6.equals(model.enums.EquipmentStatus.ENABLED));
        org.junit.Assert.assertNotNull(wildcardClass7);
    }

    @Test
    public void test084() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test084");
        java.time.LocalDateTime localDateTime1 = null;
        java.time.LocalDateTime localDateTime2 = null;
        model.Reservation reservation5 = new model.Reservation(0, localDateTime1, localDateTime2, 2, 10);
        reservation5.setId((int) (short) 1);
        java.time.LocalDateTime localDateTime8 = reservation5.getStartTime();
        reservation5.setEquipmentId((int) ' ');
        java.time.LocalDateTime localDateTime11 = null;
        reservation5.setEndTime(localDateTime11);
        java.time.LocalDateTime localDateTime13 = reservation5.getStartTime();
        org.junit.Assert.assertNull(localDateTime8);
        org.junit.Assert.assertNull(localDateTime13);
    }

    @Test
    public void test085() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test085");
        model.userhierarchy.HeadLabCoordinator headLabCoordinator0 = model.userhierarchy.HeadLabCoordinator.getInstance();
        java.lang.String str1 = headLabCoordinator0.getPassword();
        int int2 = headLabCoordinator0.getId();
        int int3 = headLabCoordinator0.getVerificationNum();
        headLabCoordinator0.initialize((int) (byte) 10, "1 hi! model.exceptions.EmailNotUniqueException:   ENABLED", "1 hi! model.exceptions.EmailNotUniqueException:   ENABLED", "model.exceptions.EmailNotUniqueException: ", 33);
        org.junit.Assert.assertNotNull(headLabCoordinator0);
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "hi!" + "'", str1, "hi!");
// flaky "10) test085(RegressionTest0)":         org.junit.Assert.assertTrue("'" + int2 + "' != '" + 33 + "'", int2 == 33);
        org.junit.Assert.assertTrue("'" + int3 + "' != '" + 12 + "'", int3 == 12);
    }

    @Test
    public void test086() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test086");
        model.userhierarchy.Guest guest5 = new model.userhierarchy.Guest(3, "hi!", "hi!", "", 100);
        java.lang.String str6 = guest5.getUsername();
        java.lang.String str7 = guest5.getEmail();
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "hi!" + "'", str6, "hi!");
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + "hi!" + "'", str7, "hi!");
    }

    @Test
    public void test087() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test087");
        java.time.LocalDateTime localDateTime1 = null;
        java.time.LocalDateTime localDateTime2 = null;
        model.Reservation reservation5 = new model.Reservation(0, localDateTime1, localDateTime2, 2, 10);
        reservation5.setDepositAmount((int) (byte) 100);
        int int8 = reservation5.getTotalOwed();
        reservation5.setId((int) ' ');
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 0 + "'", int8 == 0);
    }

    @Test
    public void test088() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test088");
        java.time.LocalDateTime localDateTime1 = null;
        java.time.LocalDateTime localDateTime2 = null;
        model.Reservation reservation5 = new model.Reservation(0, localDateTime1, localDateTime2, 2, 10);
        reservation5.setId((int) (short) 1);
        java.time.LocalDateTime localDateTime8 = reservation5.getStartTime();
        reservation5.setEquipmentId((int) ' ');
        java.time.LocalDateTime localDateTime12 = null;
        java.time.LocalDateTime localDateTime13 = null;
        model.Reservation reservation16 = new model.Reservation(0, localDateTime12, localDateTime13, 2, 10);
        reservation16.setId((int) (short) 1);
        java.time.LocalDateTime localDateTime19 = reservation16.getStartTime();
        reservation16.setEquipmentId((int) ' ');
        java.time.LocalDateTime localDateTime22 = null;
        reservation16.setEndTime(localDateTime22);
        model.enums.ReservationStatus reservationStatus24 = reservation16.getStatus();
        int int25 = reservation16.getId();
        model.enums.ReservationStatus reservationStatus26 = model.enums.ReservationStatus.CANCELLED;
        reservation16.setStatus(reservationStatus26);
        reservation5.setStatus(reservationStatus26);
        org.junit.Assert.assertNull(localDateTime8);
        org.junit.Assert.assertNull(localDateTime19);
        org.junit.Assert.assertNull(reservationStatus24);
        org.junit.Assert.assertTrue("'" + int25 + "' != '" + 1 + "'", int25 == 1);
        org.junit.Assert.assertTrue("'" + reservationStatus26 + "' != '" + model.enums.ReservationStatus.CANCELLED + "'", reservationStatus26.equals(model.enums.ReservationStatus.CANCELLED));
    }

    @Test
    public void test089() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test089");
        java.time.LocalDateTime localDateTime1 = null;
        java.time.LocalDateTime localDateTime2 = null;
        model.Reservation reservation5 = new model.Reservation(0, localDateTime1, localDateTime2, 2, 10);
        reservation5.setId((int) (short) 1);
        java.time.LocalDateTime localDateTime8 = reservation5.getStartTime();
        int int9 = reservation5.getEquipmentId();
        int int10 = reservation5.getUserId();
        model.Equipment equipment15 = new model.Equipment((int) (short) 1, "hi!", "hi!", "");
        model.Sensor sensor16 = new model.Sensor(equipment15);
        model.enums.EquipmentStatus equipmentStatus17 = equipment15.getStatus();
        model.Sensor sensor18 = new model.Sensor(equipment15);
        reservation5.update(sensor18, "");
        reservation5.cancelReservation();
        reservation5.setTotalOwed(6);
        reservation5.setTotalOwed(35);
        int int26 = reservation5.getEquipmentId();
        org.junit.Assert.assertNull(localDateTime8);
        org.junit.Assert.assertTrue("'" + int9 + "' != '" + 10 + "'", int9 == 10);
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 2 + "'", int10 == 2);
        org.junit.Assert.assertTrue("'" + equipmentStatus17 + "' != '" + model.enums.EquipmentStatus.ENABLED + "'", equipmentStatus17.equals(model.enums.EquipmentStatus.ENABLED));
        org.junit.Assert.assertTrue("'" + int26 + "' != '" + 10 + "'", int26 == 10);
    }

    @Test
    public void test090() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test090");
        model.paymentclasses.InstitutionalGrantPayment institutionalGrantPayment0 = new model.paymentclasses.InstitutionalGrantPayment();
        institutionalGrantPayment0.makePayment(2);
        institutionalGrantPayment0.makePayment(2);
    }

    @Test
    public void test091() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test091");
        model.exceptions.EmailNotUniqueException emailNotUniqueException1 = new model.exceptions.EmailNotUniqueException("");
        model.exceptions.EmailNotUniqueException emailNotUniqueException3 = new model.exceptions.EmailNotUniqueException("");
        emailNotUniqueException1.addSuppressed((java.lang.Throwable) emailNotUniqueException3);
        java.lang.Throwable[] throwableArray5 = emailNotUniqueException1.getSuppressed();
        java.lang.String str6 = emailNotUniqueException1.toString();
        org.junit.Assert.assertNotNull(throwableArray5);
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "model.exceptions.EmailNotUniqueException: " + "'", str6, "model.exceptions.EmailNotUniqueException: ");
    }

    @Test
    public void test092() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test092");
        model.exceptions.WeakPasswordException weakPasswordException1 = new model.exceptions.WeakPasswordException("");
        model.exceptions.EmailNotUniqueException emailNotUniqueException3 = new model.exceptions.EmailNotUniqueException("");
        model.exceptions.EmailNotUniqueException emailNotUniqueException5 = new model.exceptions.EmailNotUniqueException("");
        emailNotUniqueException3.addSuppressed((java.lang.Throwable) emailNotUniqueException5);
        weakPasswordException1.addSuppressed((java.lang.Throwable) emailNotUniqueException3);
    }

    @Test
    public void test093() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test093");
        model.Equipment equipment4 = new model.Equipment((int) (short) 1, "hi!", "hi!", "");
        model.Sensor sensor5 = new model.Sensor(equipment4);
        model.enums.EquipmentStatus equipmentStatus6 = equipment4.getStatus();
        equipment4.setName("");
        org.junit.Assert.assertTrue("'" + equipmentStatus6 + "' != '" + model.enums.EquipmentStatus.ENABLED + "'", equipmentStatus6.equals(model.enums.EquipmentStatus.ENABLED));
    }

    @Test
    public void test094() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test094");
        model.userhierarchy.HeadLabCoordinator headLabCoordinator0 = model.userhierarchy.HeadLabCoordinator.getInstance();
        boolean boolean1 = headLabCoordinator0.getIsDepartmentApproved();
        boolean boolean2 = headLabCoordinator0.getIsDepartmentApproved();
        int int3 = headLabCoordinator0.getHourlyRate();
        boolean boolean4 = headLabCoordinator0.getIsDepartmentApproved();
        headLabCoordinator0.setUserId(20);
        org.junit.Assert.assertNotNull(headLabCoordinator0);
// flaky "11) test094(RegressionTest0)":         org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + true + "'", boolean1 == true);
// flaky "7) test094(RegressionTest0)":         org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + true + "'", boolean2 == true);
        org.junit.Assert.assertTrue("'" + int3 + "' != '" + 0 + "'", int3 == 0);
// flaky "4) test094(RegressionTest0)":         org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + true + "'", boolean4 == true);
    }

    @Test
    public void test095() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test095");
        model.UserFactory userFactory0 = new model.UserFactory();
        model.enums.UserType userType6 = model.enums.UserType.RESEARCHER;
        model.userhierarchy.User user7 = userFactory0.createUser((int) (byte) 100, "hi!", "model.exceptions.WeakPasswordException: model.exceptions.WeakPasswordException: hi!", "", 100, userType6);
        int int8 = user7.getHourlyRate();
        org.junit.Assert.assertTrue("'" + userType6 + "' != '" + model.enums.UserType.RESEARCHER + "'", userType6.equals(model.enums.UserType.RESEARCHER));
        org.junit.Assert.assertNotNull(user7);
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 20 + "'", int8 == 20);
    }

    @Test
    public void test096() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test096");
        java.time.LocalDateTime localDateTime1 = null;
        java.time.LocalDateTime localDateTime2 = null;
        model.Reservation reservation5 = new model.Reservation(0, localDateTime1, localDateTime2, 2, 10);
        reservation5.setId((int) (short) 1);
        java.time.LocalDateTime localDateTime8 = reservation5.getStartTime();
        int int9 = reservation5.getEquipmentId();
        java.time.LocalDateTime localDateTime10 = null;
        reservation5.setStartTime(localDateTime10);
        reservation5.setId((int) 'a');
        org.junit.Assert.assertNull(localDateTime8);
        org.junit.Assert.assertTrue("'" + int9 + "' != '" + 10 + "'", int9 == 10);
    }

    @Test
    public void test097() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test097");
        model.IdGeneration idGeneration0 = model.IdGeneration.getInstance();
        int int1 = idGeneration0.nextUserId();
        int int2 = idGeneration0.nextReservationId();
        int int3 = idGeneration0.nextReservationId();
        org.junit.Assert.assertNotNull(idGeneration0);
// flaky "12) test097(RegressionTest0)":         org.junit.Assert.assertTrue("'" + int1 + "' != '" + 35 + "'", int1 == 35);
// flaky "8) test097(RegressionTest0)":         org.junit.Assert.assertTrue("'" + int2 + "' != '" + 34 + "'", int2 == 34);
// flaky "5) test097(RegressionTest0)":         org.junit.Assert.assertTrue("'" + int3 + "' != '" + 35 + "'", int3 == 35);
    }

    @Test
    public void test098() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test098");
        model.UserFactory userFactory0 = new model.UserFactory();
        model.enums.UserType userType6 = model.enums.UserType.RESEARCHER;
        model.userhierarchy.User user7 = userFactory0.createUser((int) (byte) 100, "hi!", "model.exceptions.WeakPasswordException: model.exceptions.WeakPasswordException: hi!", "", 100, userType6);
        java.lang.String str8 = user7.getPassword();
        int int9 = user7.getVerificationNum();
        java.lang.String str10 = user7.getEmail();
        org.junit.Assert.assertTrue("'" + userType6 + "' != '" + model.enums.UserType.RESEARCHER + "'", userType6.equals(model.enums.UserType.RESEARCHER));
        org.junit.Assert.assertNotNull(user7);
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "" + "'", str8, "");
        org.junit.Assert.assertTrue("'" + int9 + "' != '" + 100 + "'", int9 == 100);
        org.junit.Assert.assertEquals("'" + str10 + "' != '" + "hi!" + "'", str10, "hi!");
    }

    @Test
    public void test099() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test099");
        model.userhierarchy.HeadLabCoordinator headLabCoordinator0 = model.userhierarchy.HeadLabCoordinator.getInstance();
        boolean boolean1 = headLabCoordinator0.getUniversityAfilliated();
        org.junit.Assert.assertNotNull(headLabCoordinator0);
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + false + "'", boolean1 == false);
    }

    @Test
    public void test100() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test100");
        model.paymentclasses.DebitPayment debitPayment0 = new model.paymentclasses.DebitPayment();
        debitPayment0.makePayment((int) 'a');
    }

    @Test
    public void test101() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test101");
        java.time.LocalDateTime localDateTime1 = null;
        java.time.LocalDateTime localDateTime2 = null;
        model.Reservation reservation5 = new model.Reservation(0, localDateTime1, localDateTime2, 2, 10);
        reservation5.setId((int) (short) 1);
        java.time.LocalDateTime localDateTime8 = reservation5.getStartTime();
        reservation5.setEquipmentId((int) ' ');
        model.Equipment equipment15 = new model.Equipment((int) (short) 1, "hi!", "hi!", "");
        model.Sensor sensor16 = new model.Sensor(equipment15);
        model.enums.EquipmentStatus equipmentStatus17 = equipment15.getStatus();
        model.Sensor sensor18 = new model.Sensor(equipment15);
        model.enums.EquipmentStatus equipmentStatus19 = model.enums.EquipmentStatus.ENABLED;
        equipment15.setStatus(equipmentStatus19);
        model.Equipment equipment25 = new model.Equipment((int) (short) 1, "hi!", "hi!", "");
        equipment25.setName("");
        model.enums.EquipmentStatus equipmentStatus28 = model.enums.EquipmentStatus.ENABLED;
        equipment25.setStatus(equipmentStatus28);
        model.Sensor sensor30 = new model.Sensor(equipment25);
        equipment15.update(sensor30, "hi!");
        sensor30.notifyObservers("");
        reservation5.update(sensor30, "model.exceptions.EmailNotUniqueException: ");
        model.enums.ReservationStatus reservationStatus37 = reservation5.getStatus();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str38 = reservation5.toString();
            org.junit.Assert.fail("Expected exception of type java.lang.NullPointerException; message: Cannot invoke \"java.time.LocalDateTime.toString()\" because \"this.startTime\" is null");
        } catch (java.lang.NullPointerException e) {
            // Expected exception.
        }
        org.junit.Assert.assertNull(localDateTime8);
        org.junit.Assert.assertTrue("'" + equipmentStatus17 + "' != '" + model.enums.EquipmentStatus.ENABLED + "'", equipmentStatus17.equals(model.enums.EquipmentStatus.ENABLED));
        org.junit.Assert.assertTrue("'" + equipmentStatus19 + "' != '" + model.enums.EquipmentStatus.ENABLED + "'", equipmentStatus19.equals(model.enums.EquipmentStatus.ENABLED));
        org.junit.Assert.assertTrue("'" + equipmentStatus28 + "' != '" + model.enums.EquipmentStatus.ENABLED + "'", equipmentStatus28.equals(model.enums.EquipmentStatus.ENABLED));
        org.junit.Assert.assertNull(reservationStatus37);
    }

    @Test
    public void test102() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test102");
        model.IdGeneration idGeneration0 = model.IdGeneration.getInstance();
        int int1 = idGeneration0.nextUserId();
        idGeneration0.setEquipmentIdCount((int) (short) 10);
        int int4 = idGeneration0.nextReservationId();
        int int5 = idGeneration0.nextReservationId();
        int int6 = idGeneration0.nextEquipmentId();
        idGeneration0.setEquipmentIdCount((int) (short) 10);
        org.junit.Assert.assertNotNull(idGeneration0);
// flaky "13) test102(RegressionTest0)":         org.junit.Assert.assertTrue("'" + int1 + "' != '" + 36 + "'", int1 == 36);
// flaky "9) test102(RegressionTest0)":         org.junit.Assert.assertTrue("'" + int4 + "' != '" + 36 + "'", int4 == 36);
// flaky "6) test102(RegressionTest0)":         org.junit.Assert.assertTrue("'" + int5 + "' != '" + 37 + "'", int5 == 37);
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 10 + "'", int6 == 10);
    }

    @Test
    public void test103() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test103");
        model.UserFactory userFactory0 = new model.UserFactory();
        model.userhierarchy.Student student11 = new model.userhierarchy.Student((int) (short) 0, "hi!", "hi!", "hi!", 0);
        int int12 = student11.getHourlyRate();
        student11.setUniversityAfilliated(false);
        model.enums.UserType userType15 = student11.getUserType();
        model.enums.UserType userType16 = student11.getUserType();
        model.userhierarchy.User user17 = userFactory0.createUser(0, "", "model.exceptions.WeakPasswordException: model.exceptions.WeakPasswordException: hi!", "model.exceptions.WeakPasswordException: model.exceptions.WeakPasswordException: hi!", 4, userType16);
        org.junit.Assert.assertTrue("'" + int12 + "' != '" + 10 + "'", int12 == 10);
        org.junit.Assert.assertTrue("'" + userType15 + "' != '" + model.enums.UserType.STUDENT + "'", userType15.equals(model.enums.UserType.STUDENT));
        org.junit.Assert.assertTrue("'" + userType16 + "' != '" + model.enums.UserType.STUDENT + "'", userType16.equals(model.enums.UserType.STUDENT));
        org.junit.Assert.assertNotNull(user17);
    }

    @Test
    public void test104() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test104");
        model.userhierarchy.HeadLabCoordinator headLabCoordinator0 = model.userhierarchy.HeadLabCoordinator.getInstance();
        java.lang.String str1 = headLabCoordinator0.getPassword();
        int int2 = headLabCoordinator0.getId();
        java.lang.String str3 = headLabCoordinator0.getEmail();
        boolean boolean4 = headLabCoordinator0.getIsDepartmentApproved();
        org.junit.Assert.assertNotNull(headLabCoordinator0);
// flaky "14) test104(RegressionTest0)":         org.junit.Assert.assertEquals("'" + str1 + "' != '" + "" + "'", str1, "");
// flaky "10) test104(RegressionTest0)":         org.junit.Assert.assertTrue("'" + int2 + "' != '" + 9 + "'", int2 == 9);
// flaky "7) test104(RegressionTest0)":         org.junit.Assert.assertEquals("'" + str3 + "' != '" + "" + "'", str3, "");
// flaky "3) test104(RegressionTest0)":         org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + true + "'", boolean4 == true);
    }

    @Test
    public void test105() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test105");
        java.time.LocalDateTime localDateTime1 = null;
        java.time.LocalDateTime localDateTime2 = null;
        model.Reservation reservation5 = new model.Reservation(11, localDateTime1, localDateTime2, 1, 12);
    }

    @Test
    public void test106() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test106");
        model.paymentclasses.InstitutionalGrantPayment institutionalGrantPayment0 = new model.paymentclasses.InstitutionalGrantPayment();
        institutionalGrantPayment0.makePayment((int) (short) 0);
        institutionalGrantPayment0.makePayment((-1));
    }

    @Test
    public void test107() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test107");
        model.Equipment equipment4 = new model.Equipment((int) (short) 1, "hi!", "hi!", "");
        java.lang.String str5 = equipment4.getLabLocation();
        java.lang.String str6 = equipment4.getLabLocation();
        java.lang.String str7 = equipment4.getName();
        model.Sensor sensor8 = equipment4.getSensor();
        equipment4.setName("model.exceptions.WeakPasswordException: hi!");
        org.junit.Assert.assertEquals("'" + str5 + "' != '" + "" + "'", str5, "");
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "" + "'", str6, "");
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + "hi!" + "'", str7, "hi!");
        org.junit.Assert.assertNotNull(sensor8);
    }

    @Test
    public void test108() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test108");
        model.userhierarchy.Faculty faculty5 = new model.userhierarchy.Faculty((int) '4', "hi!", "", "model.exceptions.WeakPasswordException: hi!", (int) '#');
    }

    @Test
    public void test109() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test109");
        model.IdGeneration idGeneration0 = model.IdGeneration.getInstance();
        int int1 = idGeneration0.nextUserId();
        int int2 = idGeneration0.nextReservationId();
        idGeneration0.setEquipmentIdCount((int) (byte) 0);
        idGeneration0.setUserIdCount(10);
        idGeneration0.setUserIdCount((int) (byte) 0);
        org.junit.Assert.assertNotNull(idGeneration0);
// flaky "15) test109(RegressionTest0)":         org.junit.Assert.assertTrue("'" + int1 + "' != '" + 37 + "'", int1 == 37);
// flaky "11) test109(RegressionTest0)":         org.junit.Assert.assertTrue("'" + int2 + "' != '" + 38 + "'", int2 == 38);
    }

    @Test
    public void test110() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test110");
        model.Equipment equipment4 = new model.Equipment((int) (short) 1, "hi!", "hi!", "");
        java.lang.String str5 = equipment4.getLabLocation();
        equipment4.setLabLocation("model.exceptions.WeakPasswordException: hi!");
        java.lang.String str8 = equipment4.toString();
        org.junit.Assert.assertEquals("'" + str5 + "' != '" + "" + "'", str5, "");
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "1 hi! hi! model.exceptions.WeakPasswordException: hi! ENABLED" + "'", str8, "1 hi! hi! model.exceptions.WeakPasswordException: hi! ENABLED");
    }

    @Test
    public void test111() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test111");
        model.userhierarchy.HeadLabCoordinator headLabCoordinator0 = model.userhierarchy.HeadLabCoordinator.getInstance();
        boolean boolean1 = headLabCoordinator0.getIsDepartmentApproved();
        boolean boolean2 = headLabCoordinator0.getIsDepartmentApproved();
        headLabCoordinator0.setUserId((int) (byte) 1);
        int int5 = headLabCoordinator0.getVerificationNum();
        headLabCoordinator0.setIsDepartmentApproved(true);
        org.junit.Assert.assertNotNull(headLabCoordinator0);
// flaky "16) test111(RegressionTest0)":         org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + true + "'", boolean1 == true);
// flaky "12) test111(RegressionTest0)":         org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + true + "'", boolean2 == true);
// flaky "8) test111(RegressionTest0)":         org.junit.Assert.assertTrue("'" + int5 + "' != '" + 12 + "'", int5 == 12);
    }

    @Test
    public void test112() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test112");
        model.userhierarchy.Student student5 = new model.userhierarchy.Student(14, "1 model.exceptions.WeakPasswordException: hi! hi!  ENABLED", "1 model.exceptions.WeakPasswordException: hi! hi!  ENABLED", "model.exceptions.WeakPasswordException: hi!", 6);
    }

    @Test
    public void test113() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test113");
        model.userhierarchy.Student student5 = new model.userhierarchy.Student((-1), "model.exceptions.WeakPasswordException: hi!", "hi!", "model.exceptions.EmailNotUniqueException: ", 34);
    }

    @Test
    public void test114() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test114");
        model.Equipment equipment4 = new model.Equipment((int) (short) 1, "hi!", "hi!", "");
        equipment4.setName("");
        model.Sensor sensor7 = new model.Sensor(equipment4);
    }

    @Test
    public void test115() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test115");
        model.Equipment equipment4 = new model.Equipment(9, "1 hi! hi! model.exceptions.WeakPasswordException: hi! ENABLED", "1 hi! model.exceptions.EmailNotUniqueException:   ENABLED", "model.exceptions.EmailNotUniqueException: ");
    }

    @Test
    public void test116() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test116");
        java.time.LocalDateTime localDateTime1 = null;
        java.time.LocalDateTime localDateTime2 = null;
        model.Reservation reservation5 = new model.Reservation(0, localDateTime1, localDateTime2, 2, 10);
        int int6 = reservation5.getTotalOwed();
        java.time.LocalDateTime localDateTime7 = reservation5.getEndTime();
        reservation5.setId((int) 'a');
        reservation5.setDepositAmount((int) '#');
        int int12 = reservation5.getEquipmentId();
        int int13 = reservation5.getUserId();
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 0 + "'", int6 == 0);
        org.junit.Assert.assertNull(localDateTime7);
        org.junit.Assert.assertTrue("'" + int12 + "' != '" + 10 + "'", int12 == 10);
        org.junit.Assert.assertTrue("'" + int13 + "' != '" + 2 + "'", int13 == 2);
    }

    @Test
    public void test117() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test117");
        model.Equipment equipment4 = new model.Equipment((int) (short) 1, "hi!", "hi!", "");
        equipment4.setLabLocation("model.exceptions.WeakPasswordException: hi!");
    }

    @Test
    public void test118() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test118");
        model.Equipment equipment4 = new model.Equipment((int) (short) 1, "hi!", "hi!", "");
        model.Sensor sensor5 = new model.Sensor(equipment4);
        model.enums.EquipmentStatus equipmentStatus6 = equipment4.getStatus();
        model.Sensor sensor7 = equipment4.getSensor();
        sensor7.notifyObservers("");
        org.junit.Assert.assertTrue("'" + equipmentStatus6 + "' != '" + model.enums.EquipmentStatus.ENABLED + "'", equipmentStatus6.equals(model.enums.EquipmentStatus.ENABLED));
        org.junit.Assert.assertNotNull(sensor7);
    }

    @Test
    public void test119() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test119");
        model.Equipment equipment4 = new model.Equipment((int) (short) 1, "hi!", "hi!", "");
        equipment4.setName("");
        equipment4.setDescription("hi!");
        equipment4.setDescription("");
        model.Sensor sensor11 = new model.Sensor(equipment4);
        sensor11.departure();
        sensor11.notifyObservers("");
    }

    @Test
    public void test120() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test120");
        model.userhierarchy.Student student5 = new model.userhierarchy.Student((int) (short) 0, "hi!", "hi!", "hi!", 0);
        int int6 = student5.getHourlyRate();
        student5.setUniversityAfilliated(false);
        int int9 = student5.getId();
        int int10 = student5.getVerificationNum();
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 10 + "'", int6 == 10);
        org.junit.Assert.assertTrue("'" + int9 + "' != '" + 0 + "'", int9 == 0);
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 0 + "'", int10 == 0);
    }

    @Test
    public void test121() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test121");
        java.time.LocalDateTime localDateTime1 = null;
        java.time.LocalDateTime localDateTime2 = null;
        model.Reservation reservation5 = new model.Reservation(33, localDateTime1, localDateTime2, (int) '4', 35);
        reservation5.setUserId(5);
    }

    @Test
    public void test122() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test122");
        model.Equipment equipment4 = new model.Equipment((int) (short) 1, "hi!", "hi!", "");
        equipment4.setName("");
        java.lang.String str7 = equipment4.toString();
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + "1  hi!  ENABLED" + "'", str7, "1  hi!  ENABLED");
    }

    @Test
    public void test123() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test123");
        model.userhierarchy.HeadLabCoordinator headLabCoordinator0 = model.userhierarchy.HeadLabCoordinator.getInstance();
        boolean boolean1 = headLabCoordinator0.getIsDepartmentApproved();
        boolean boolean2 = headLabCoordinator0.getIsDepartmentApproved();
        headLabCoordinator0.setUserId((int) (byte) 1);
        int int5 = headLabCoordinator0.getUserId();
        headLabCoordinator0.initialize(37, "model.exceptions.WeakPasswordException: hi!", "hi!", "", 8);
        org.junit.Assert.assertNotNull(headLabCoordinator0);
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + true + "'", boolean1 == true);
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + true + "'", boolean2 == true);
        org.junit.Assert.assertTrue("'" + int5 + "' != '" + 1 + "'", int5 == 1);
    }

    @Test
    public void test124() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test124");
        model.IdGeneration idGeneration0 = model.IdGeneration.getInstance();
        int int1 = idGeneration0.nextUserId();
        int int2 = idGeneration0.nextReservationId();
        idGeneration0.setEquipmentIdCount((int) (byte) 0);
        int int5 = idGeneration0.nextEquipmentId();
        idGeneration0.setReservationIdCount((int) '4');
        org.junit.Assert.assertNotNull(idGeneration0);
        org.junit.Assert.assertTrue("'" + int1 + "' != '" + 0 + "'", int1 == 0);
// flaky "17) test124(RegressionTest0)":         org.junit.Assert.assertTrue("'" + int2 + "' != '" + 39 + "'", int2 == 39);
        org.junit.Assert.assertTrue("'" + int5 + "' != '" + 0 + "'", int5 == 0);
    }

    @Test
    public void test125() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test125");
        java.time.LocalDateTime localDateTime1 = null;
        java.time.LocalDateTime localDateTime2 = null;
        model.Reservation reservation5 = new model.Reservation(0, localDateTime1, localDateTime2, 2, 10);
        reservation5.setId((int) (short) 1);
        java.time.LocalDateTime localDateTime8 = reservation5.getStartTime();
        int int9 = reservation5.getEquipmentId();
        int int10 = reservation5.getUserId();
        model.Equipment equipment15 = new model.Equipment((int) (short) 1, "hi!", "hi!", "");
        model.Sensor sensor16 = new model.Sensor(equipment15);
        model.enums.EquipmentStatus equipmentStatus17 = equipment15.getStatus();
        model.Sensor sensor18 = new model.Sensor(equipment15);
        reservation5.update(sensor18, "");
        model.Equipment equipment25 = new model.Equipment((int) (short) 1, "hi!", "hi!", "");
        java.lang.String str26 = equipment25.getName();
        sensor18.detach((model.SensorObserver) equipment25);
        org.junit.Assert.assertNull(localDateTime8);
        org.junit.Assert.assertTrue("'" + int9 + "' != '" + 10 + "'", int9 == 10);
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 2 + "'", int10 == 2);
        org.junit.Assert.assertTrue("'" + equipmentStatus17 + "' != '" + model.enums.EquipmentStatus.ENABLED + "'", equipmentStatus17.equals(model.enums.EquipmentStatus.ENABLED));
        org.junit.Assert.assertEquals("'" + str26 + "' != '" + "hi!" + "'", str26, "hi!");
    }

    @Test
    public void test126() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test126");
        model.userhierarchy.Guest guest5 = new model.userhierarchy.Guest(52, "1  hi!  ENABLED", "1 model.exceptions.WeakPasswordException: hi! hi!  ENABLED", "", (int) (byte) -1);
    }

    @Test
    public void test127() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test127");
        java.time.LocalDateTime localDateTime1 = null;
        java.time.LocalDateTime localDateTime2 = null;
        model.Reservation reservation5 = new model.Reservation(0, localDateTime1, localDateTime2, 2, 10);
        reservation5.setId((int) (short) 1);
        java.time.LocalDateTime localDateTime8 = reservation5.getStartTime();
        int int9 = reservation5.getEquipmentId();
        int int10 = reservation5.getUserId();
        int int11 = reservation5.getTotalOwed();
        java.time.LocalDateTime localDateTime12 = reservation5.getEndTime();
        reservation5.setDepositAmount(11);
        java.time.LocalDateTime localDateTime15 = reservation5.getEndTime();
        org.junit.Assert.assertNull(localDateTime8);
        org.junit.Assert.assertTrue("'" + int9 + "' != '" + 10 + "'", int9 == 10);
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 2 + "'", int10 == 2);
        org.junit.Assert.assertTrue("'" + int11 + "' != '" + 0 + "'", int11 == 0);
        org.junit.Assert.assertNull(localDateTime12);
        org.junit.Assert.assertNull(localDateTime15);
    }

    @Test
    public void test128() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test128");
        model.Equipment equipment4 = new model.Equipment((int) (short) 1, "hi!", "hi!", "");
        model.Sensor sensor5 = new model.Sensor(equipment4);
        model.enums.EquipmentStatus equipmentStatus6 = equipment4.getStatus();
        model.Sensor sensor7 = new model.Sensor(equipment4);
        model.enums.EquipmentStatus equipmentStatus8 = model.enums.EquipmentStatus.ENABLED;
        equipment4.setStatus(equipmentStatus8);
        model.Equipment equipment14 = new model.Equipment((int) (short) 1, "hi!", "hi!", "");
        equipment14.setName("");
        model.enums.EquipmentStatus equipmentStatus17 = model.enums.EquipmentStatus.ENABLED;
        equipment14.setStatus(equipmentStatus17);
        model.Sensor sensor19 = new model.Sensor(equipment14);
        equipment4.update(sensor19, "hi!");
        sensor19.notifyObservers("");
        sensor19.notifyObservers("1 model.exceptions.WeakPasswordException: hi! hi!  ENABLED");
        org.junit.Assert.assertTrue("'" + equipmentStatus6 + "' != '" + model.enums.EquipmentStatus.ENABLED + "'", equipmentStatus6.equals(model.enums.EquipmentStatus.ENABLED));
        org.junit.Assert.assertTrue("'" + equipmentStatus8 + "' != '" + model.enums.EquipmentStatus.ENABLED + "'", equipmentStatus8.equals(model.enums.EquipmentStatus.ENABLED));
        org.junit.Assert.assertTrue("'" + equipmentStatus17 + "' != '" + model.enums.EquipmentStatus.ENABLED + "'", equipmentStatus17.equals(model.enums.EquipmentStatus.ENABLED));
    }

    @Test
    public void test129() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test129");
        model.Equipment equipment4 = new model.Equipment((int) (short) 1, "hi!", "hi!", "");
        model.Sensor sensor5 = new model.Sensor(equipment4);
        model.enums.EquipmentStatus equipmentStatus6 = equipment4.getStatus();
        model.Sensor sensor7 = new model.Sensor(equipment4);
        model.enums.EquipmentStatus equipmentStatus8 = model.enums.EquipmentStatus.ENABLED;
        equipment4.setStatus(equipmentStatus8);
        equipment4.setLabLocation("hi!");
        model.Equipment equipment16 = new model.Equipment((int) (short) 1, "hi!", "hi!", "");
        model.enums.EquipmentStatus equipmentStatus17 = model.enums.EquipmentStatus.ENABLED;
        equipment16.setStatus(equipmentStatus17);
        equipment4.setStatus(equipmentStatus17);
        org.junit.Assert.assertTrue("'" + equipmentStatus6 + "' != '" + model.enums.EquipmentStatus.ENABLED + "'", equipmentStatus6.equals(model.enums.EquipmentStatus.ENABLED));
        org.junit.Assert.assertTrue("'" + equipmentStatus8 + "' != '" + model.enums.EquipmentStatus.ENABLED + "'", equipmentStatus8.equals(model.enums.EquipmentStatus.ENABLED));
        org.junit.Assert.assertTrue("'" + equipmentStatus17 + "' != '" + model.enums.EquipmentStatus.ENABLED + "'", equipmentStatus17.equals(model.enums.EquipmentStatus.ENABLED));
    }

    @Test
    public void test130() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test130");
        model.userhierarchy.HeadLabCoordinator headLabCoordinator0 = model.userhierarchy.HeadLabCoordinator.getInstance();
        headLabCoordinator0.setUniversityAfilliated(true);
        org.junit.Assert.assertNotNull(headLabCoordinator0);
    }

    @Test
    public void test131() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test131");
        model.userhierarchy.LabManager labManager5 = new model.userhierarchy.LabManager((int) '4', "model.exceptions.WeakPasswordException: hi!", "hi!", "model.exceptions.EmailNotUniqueException: ", 35);
    }

    @Test
    public void test132() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test132");
        model.Equipment equipment4 = new model.Equipment((int) (short) 1, "1 model.exceptions.WeakPasswordException: hi! hi!  ENABLED", "model.exceptions.WeakPasswordException: model.exceptions.WeakPasswordException: hi!", "model.exceptions.EmailNotUniqueException: ");
    }

    @Test
    public void test133() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test133");
        model.userhierarchy.Researcher researcher5 = new model.userhierarchy.Researcher((int) '#', "model.exceptions.EmailNotUniqueException: ", "1 hi! hi! model.exceptions.WeakPasswordException: hi! ENABLED", "1 hi! hi! model.exceptions.WeakPasswordException: hi! ENABLED", (int) (short) -1);
    }

    @Test
    public void test134() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test134");
        model.Equipment equipment4 = new model.Equipment((int) (short) 1, "hi!", "hi!", "");
        model.Sensor sensor5 = new model.Sensor(equipment4);
        model.enums.EquipmentStatus equipmentStatus6 = equipment4.getStatus();
        model.Sensor sensor7 = new model.Sensor(equipment4);
        equipment4.setLabLocation("1  hi!  ENABLED");
        org.junit.Assert.assertTrue("'" + equipmentStatus6 + "' != '" + model.enums.EquipmentStatus.ENABLED + "'", equipmentStatus6.equals(model.enums.EquipmentStatus.ENABLED));
    }

    @Test
    public void test135() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test135");
        model.Equipment equipment4 = new model.Equipment((int) (short) 1, "hi!", "hi!", "");
        java.lang.String str5 = equipment4.getLabLocation();
        model.Sensor sensor6 = equipment4.getSensor();
        sensor6.notifyObservers("");
        model.Equipment equipment13 = new model.Equipment((int) (short) 1, "hi!", "hi!", "");
        java.lang.String str14 = equipment13.getLabLocation();
        model.Sensor sensor15 = equipment13.getSensor();
        sensor6.detach((model.SensorObserver) equipment13);
        java.lang.String str17 = equipment13.getDescription();
        org.junit.Assert.assertEquals("'" + str5 + "' != '" + "" + "'", str5, "");
        org.junit.Assert.assertNotNull(sensor6);
        org.junit.Assert.assertEquals("'" + str14 + "' != '" + "" + "'", str14, "");
        org.junit.Assert.assertNotNull(sensor15);
        org.junit.Assert.assertEquals("'" + str17 + "' != '" + "hi!" + "'", str17, "hi!");
    }

    @Test
    public void test136() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test136");
        java.time.LocalDateTime localDateTime1 = null;
        java.time.LocalDateTime localDateTime2 = null;
        model.Reservation reservation5 = new model.Reservation(0, localDateTime1, localDateTime2, 2, 10);
        reservation5.setId((int) (short) 1);
        java.time.LocalDateTime localDateTime8 = reservation5.getStartTime();
        int int9 = reservation5.getEquipmentId();
        int int10 = reservation5.getUserId();
        reservation5.cancelReservation();
        org.junit.Assert.assertNull(localDateTime8);
        org.junit.Assert.assertTrue("'" + int9 + "' != '" + 10 + "'", int9 == 10);
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 2 + "'", int10 == 2);
    }

    @Test
    public void test137() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test137");
        model.paymentclasses.ResearchGrantPayment researchGrantPayment0 = new model.paymentclasses.ResearchGrantPayment();
        researchGrantPayment0.makePayment(4);
        researchGrantPayment0.makePayment(0);
    }

    @Test
    public void test138() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test138");
        model.Equipment equipment4 = new model.Equipment((int) (short) 1, "hi!", "hi!", "");
        java.lang.String str5 = equipment4.getLabLocation();
        model.Sensor sensor6 = equipment4.getSensor();
        sensor6.departure();
        model.Equipment equipment8 = sensor6.getEquipment();
        int int9 = equipment8.getId();
        org.junit.Assert.assertEquals("'" + str5 + "' != '" + "" + "'", str5, "");
        org.junit.Assert.assertNotNull(sensor6);
        org.junit.Assert.assertNotNull(equipment8);
        org.junit.Assert.assertTrue("'" + int9 + "' != '" + 1 + "'", int9 == 1);
    }

    @Test
    public void test139() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test139");
        java.time.LocalDateTime localDateTime1 = null;
        java.time.LocalDateTime localDateTime2 = null;
        model.Reservation reservation5 = new model.Reservation(0, localDateTime1, localDateTime2, 2, 10);
        reservation5.setId((int) (short) 1);
        java.time.LocalDateTime localDateTime8 = reservation5.getStartTime();
        int int9 = reservation5.getEquipmentId();
        int int10 = reservation5.getUserId();
        model.Equipment equipment15 = new model.Equipment((int) (short) 1, "hi!", "hi!", "");
        model.Sensor sensor16 = new model.Sensor(equipment15);
        model.enums.EquipmentStatus equipmentStatus17 = equipment15.getStatus();
        model.Sensor sensor18 = new model.Sensor(equipment15);
        reservation5.update(sensor18, "");
        sensor18.notifyObservers("");
        model.Equipment equipment27 = new model.Equipment((int) (short) 1, "hi!", "hi!", "");
        equipment27.setName("");
        equipment27.setDescription("hi!");
        equipment27.setDescription("");
        equipment27.setDescription("hi!");
        sensor18.detach((model.SensorObserver) equipment27);
        org.junit.Assert.assertNull(localDateTime8);
        org.junit.Assert.assertTrue("'" + int9 + "' != '" + 10 + "'", int9 == 10);
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 2 + "'", int10 == 2);
        org.junit.Assert.assertTrue("'" + equipmentStatus17 + "' != '" + model.enums.EquipmentStatus.ENABLED + "'", equipmentStatus17.equals(model.enums.EquipmentStatus.ENABLED));
    }

    @Test
    public void test140() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test140");
        model.userhierarchy.Student student5 = new model.userhierarchy.Student((int) (short) 0, "hi!", "hi!", "hi!", 0);
        int int6 = student5.getHourlyRate();
        java.lang.String str7 = student5.getUsername();
        int int8 = student5.getId();
        int int9 = student5.getHourlyRate();
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 10 + "'", int6 == 10);
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + "hi!" + "'", str7, "hi!");
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 0 + "'", int8 == 0);
        org.junit.Assert.assertTrue("'" + int9 + "' != '" + 10 + "'", int9 == 10);
    }

    @Test
    public void test141() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test141");
        java.time.LocalDateTime localDateTime1 = null;
        java.time.LocalDateTime localDateTime2 = null;
        model.Reservation reservation5 = new model.Reservation(0, localDateTime1, localDateTime2, 2, 10);
        reservation5.setId((int) (short) 1);
        reservation5.setId((int) 'a');
        java.time.LocalDateTime localDateTime10 = reservation5.getEndTime();
        org.junit.Assert.assertNull(localDateTime10);
    }

    @Test
    public void test142() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test142");
        model.paymentclasses.ResearchGrantPayment researchGrantPayment0 = new model.paymentclasses.ResearchGrantPayment();
        researchGrantPayment0.makePayment((int) (short) -1);
        researchGrantPayment0.makePayment((int) (short) 100);
    }

    @Test
    public void test143() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test143");
        model.userhierarchy.Guest guest5 = new model.userhierarchy.Guest((int) (short) 10, "", "model.exceptions.EmailNotUniqueException: ", "1  hi!  ENABLED", 3);
        boolean boolean6 = guest5.getUniversityAfilliated();
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
    }

    @Test
    public void test144() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test144");
        model.paymentclasses.ResearchGrantPayment researchGrantPayment0 = new model.paymentclasses.ResearchGrantPayment();
        researchGrantPayment0.makePayment(4);
        researchGrantPayment0.makePayment(2);
    }

    @Test
    public void test145() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test145");
        java.time.LocalDateTime localDateTime1 = null;
        java.time.LocalDateTime localDateTime2 = null;
        model.Reservation reservation5 = new model.Reservation(0, localDateTime1, localDateTime2, 2, 10);
        int int6 = reservation5.getTotalOwed();
        java.time.LocalDateTime localDateTime7 = reservation5.getEndTime();
        java.time.LocalDateTime localDateTime8 = reservation5.getStartTime();
        model.Equipment equipment13 = new model.Equipment((int) (short) 1, "hi!", "hi!", "");
        model.Sensor sensor14 = new model.Sensor(equipment13);
        model.enums.EquipmentStatus equipmentStatus15 = equipment13.getStatus();
        model.Sensor sensor16 = new model.Sensor(equipment13);
        reservation5.update(sensor16, "model.exceptions.WeakPasswordException: hi!");
        int int19 = reservation5.getDepositAmount();
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 0 + "'", int6 == 0);
        org.junit.Assert.assertNull(localDateTime7);
        org.junit.Assert.assertNull(localDateTime8);
        org.junit.Assert.assertTrue("'" + equipmentStatus15 + "' != '" + model.enums.EquipmentStatus.ENABLED + "'", equipmentStatus15.equals(model.enums.EquipmentStatus.ENABLED));
        org.junit.Assert.assertTrue("'" + int19 + "' != '" + 0 + "'", int19 == 0);
    }

    @Test
    public void test146() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test146");
        model.userhierarchy.HeadLabCoordinator headLabCoordinator0 = model.userhierarchy.HeadLabCoordinator.getInstance();
        boolean boolean1 = headLabCoordinator0.getIsDepartmentApproved();
        boolean boolean2 = headLabCoordinator0.getIsDepartmentApproved();
        headLabCoordinator0.setUserId((int) (byte) 1);
        int int5 = headLabCoordinator0.getUserId();
        int int6 = headLabCoordinator0.getUserId();
        org.junit.Assert.assertNotNull(headLabCoordinator0);
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + true + "'", boolean1 == true);
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + true + "'", boolean2 == true);
        org.junit.Assert.assertTrue("'" + int5 + "' != '" + 1 + "'", int5 == 1);
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 1 + "'", int6 == 1);
    }

    @Test
    public void test147() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test147");
        model.userhierarchy.Student student5 = new model.userhierarchy.Student((int) (short) 10, "1 hi! hi! model.exceptions.WeakPasswordException: hi! ENABLED", "", "1 model.exceptions.WeakPasswordException: hi! hi!  ENABLED", 20);
    }

    @Test
    public void test148() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test148");
        java.time.LocalDateTime localDateTime1 = null;
        java.time.LocalDateTime localDateTime2 = null;
        model.Reservation reservation5 = new model.Reservation(0, localDateTime1, localDateTime2, 2, 10);
        reservation5.setId((int) (short) 1);
        java.time.LocalDateTime localDateTime8 = reservation5.getStartTime();
        reservation5.setUserId((int) 'a');
        java.time.LocalDateTime localDateTime11 = reservation5.getStartTime();
        int int12 = reservation5.getUserId();
        org.junit.Assert.assertNull(localDateTime8);
        org.junit.Assert.assertNull(localDateTime11);
        org.junit.Assert.assertTrue("'" + int12 + "' != '" + 97 + "'", int12 == 97);
    }

    @Test
    public void test149() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test149");
        model.IdGeneration idGeneration0 = model.IdGeneration.getInstance();
        int int1 = idGeneration0.nextUserId();
        idGeneration0.setEquipmentIdCount((int) (short) 10);
        idGeneration0.setReservationIdCount(33);
        idGeneration0.setReservationIdCount(20);
        org.junit.Assert.assertNotNull(idGeneration0);
        org.junit.Assert.assertTrue("'" + int1 + "' != '" + 1 + "'", int1 == 1);
    }

    @Test
    public void test150() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test150");
        java.time.LocalDateTime localDateTime1 = null;
        java.time.LocalDateTime localDateTime2 = null;
        model.Reservation reservation5 = new model.Reservation(0, localDateTime1, localDateTime2, 2, 10);
        reservation5.setId((int) (short) 1);
        java.time.LocalDateTime localDateTime8 = reservation5.getStartTime();
        int int9 = reservation5.getEquipmentId();
        int int10 = reservation5.getUserId();
        model.Equipment equipment15 = new model.Equipment((int) (short) 1, "hi!", "hi!", "");
        model.Sensor sensor16 = new model.Sensor(equipment15);
        model.enums.EquipmentStatus equipmentStatus17 = equipment15.getStatus();
        model.Sensor sensor18 = new model.Sensor(equipment15);
        reservation5.update(sensor18, "");
        model.Equipment equipment25 = new model.Equipment((int) (short) 1, "hi!", "hi!", "");
        model.Sensor sensor26 = new model.Sensor(equipment25);
        model.enums.EquipmentStatus equipmentStatus27 = equipment25.getStatus();
        model.Sensor sensor28 = new model.Sensor(equipment25);
        model.enums.EquipmentStatus equipmentStatus29 = model.enums.EquipmentStatus.ENABLED;
        equipment25.setStatus(equipmentStatus29);
        model.Equipment equipment35 = new model.Equipment((int) (short) 1, "hi!", "hi!", "");
        equipment35.setName("");
        model.enums.EquipmentStatus equipmentStatus38 = model.enums.EquipmentStatus.ENABLED;
        equipment35.setStatus(equipmentStatus38);
        model.Sensor sensor40 = new model.Sensor(equipment35);
        equipment25.update(sensor40, "hi!");
        sensor18.attach((model.SensorObserver) equipment25);
        java.lang.String str44 = equipment25.getDescription();
        int int45 = equipment25.getId();
        org.junit.Assert.assertNull(localDateTime8);
        org.junit.Assert.assertTrue("'" + int9 + "' != '" + 10 + "'", int9 == 10);
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 2 + "'", int10 == 2);
        org.junit.Assert.assertTrue("'" + equipmentStatus17 + "' != '" + model.enums.EquipmentStatus.ENABLED + "'", equipmentStatus17.equals(model.enums.EquipmentStatus.ENABLED));
        org.junit.Assert.assertTrue("'" + equipmentStatus27 + "' != '" + model.enums.EquipmentStatus.ENABLED + "'", equipmentStatus27.equals(model.enums.EquipmentStatus.ENABLED));
        org.junit.Assert.assertTrue("'" + equipmentStatus29 + "' != '" + model.enums.EquipmentStatus.ENABLED + "'", equipmentStatus29.equals(model.enums.EquipmentStatus.ENABLED));
        org.junit.Assert.assertTrue("'" + equipmentStatus38 + "' != '" + model.enums.EquipmentStatus.ENABLED + "'", equipmentStatus38.equals(model.enums.EquipmentStatus.ENABLED));
        org.junit.Assert.assertEquals("'" + str44 + "' != '" + "hi!" + "'", str44, "hi!");
        org.junit.Assert.assertTrue("'" + int45 + "' != '" + 1 + "'", int45 == 1);
    }

    @Test
    public void test151() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test151");
        model.Equipment equipment4 = new model.Equipment(9, "model.exceptions.EmailNotUniqueException: ", "1 hi! hi! model.exceptions.WeakPasswordException: hi! ENABLED", "1 hi! model.exceptions.EmailNotUniqueException:   ENABLED");
    }

    @Test
    public void test152() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test152");
        model.userhierarchy.HeadLabCoordinator headLabCoordinator0 = model.userhierarchy.HeadLabCoordinator.getInstance();
        java.lang.String str1 = headLabCoordinator0.getPassword();
        int int2 = headLabCoordinator0.getHourlyRate();
        headLabCoordinator0.setUniversityAfilliated(true);
        org.junit.Assert.assertNotNull(headLabCoordinator0);
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "" + "'", str1, "");
        org.junit.Assert.assertTrue("'" + int2 + "' != '" + 0 + "'", int2 == 0);
    }

    @Test
    public void test153() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test153");
        model.Equipment equipment4 = new model.Equipment((int) (short) 1, "hi!", "hi!", "");
        model.Sensor sensor5 = new model.Sensor(equipment4);
        model.enums.EquipmentStatus equipmentStatus6 = equipment4.getStatus();
        model.Sensor sensor7 = new model.Sensor(equipment4);
        model.enums.EquipmentStatus equipmentStatus8 = model.enums.EquipmentStatus.ENABLED;
        equipment4.setStatus(equipmentStatus8);
        model.Equipment equipment14 = new model.Equipment((int) (short) 1, "hi!", "hi!", "");
        equipment14.setName("");
        model.enums.EquipmentStatus equipmentStatus17 = model.enums.EquipmentStatus.ENABLED;
        equipment14.setStatus(equipmentStatus17);
        model.Sensor sensor19 = new model.Sensor(equipment14);
        equipment4.update(sensor19, "hi!");
        model.Equipment equipment26 = new model.Equipment((int) (short) 1, "hi!", "hi!", "");
        model.Sensor sensor27 = new model.Sensor(equipment26);
        model.enums.EquipmentStatus equipmentStatus28 = equipment26.getStatus();
        model.Sensor sensor29 = new model.Sensor(equipment26);
        equipment26.setName("hi!");
        sensor19.detach((model.SensorObserver) equipment26);
        model.Equipment equipment37 = new model.Equipment((int) (short) 1, "hi!", "hi!", "");
        java.lang.String str38 = equipment37.getLabLocation();
        java.lang.String str39 = equipment37.getLabLocation();
        java.lang.String str40 = equipment37.getLabLocation();
        equipment37.setDescription("model.exceptions.WeakPasswordException: hi!");
        sensor19.detach((model.SensorObserver) equipment37);
        org.junit.Assert.assertTrue("'" + equipmentStatus6 + "' != '" + model.enums.EquipmentStatus.ENABLED + "'", equipmentStatus6.equals(model.enums.EquipmentStatus.ENABLED));
        org.junit.Assert.assertTrue("'" + equipmentStatus8 + "' != '" + model.enums.EquipmentStatus.ENABLED + "'", equipmentStatus8.equals(model.enums.EquipmentStatus.ENABLED));
        org.junit.Assert.assertTrue("'" + equipmentStatus17 + "' != '" + model.enums.EquipmentStatus.ENABLED + "'", equipmentStatus17.equals(model.enums.EquipmentStatus.ENABLED));
        org.junit.Assert.assertTrue("'" + equipmentStatus28 + "' != '" + model.enums.EquipmentStatus.ENABLED + "'", equipmentStatus28.equals(model.enums.EquipmentStatus.ENABLED));
        org.junit.Assert.assertEquals("'" + str38 + "' != '" + "" + "'", str38, "");
        org.junit.Assert.assertEquals("'" + str39 + "' != '" + "" + "'", str39, "");
        org.junit.Assert.assertEquals("'" + str40 + "' != '" + "" + "'", str40, "");
    }

    @Test
    public void test154() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test154");
        model.IdGeneration idGeneration0 = model.IdGeneration.getInstance();
        int int1 = idGeneration0.nextUserId();
        idGeneration0.setEquipmentIdCount((int) (short) 10);
        int int4 = idGeneration0.nextReservationId();
        int int5 = idGeneration0.nextReservationId();
        idGeneration0.setEquipmentIdCount(33);
        org.junit.Assert.assertNotNull(idGeneration0);
        org.junit.Assert.assertTrue("'" + int1 + "' != '" + 2 + "'", int1 == 2);
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 20 + "'", int4 == 20);
        org.junit.Assert.assertTrue("'" + int5 + "' != '" + 21 + "'", int5 == 21);
    }

    @Test
    public void test155() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test155");
        java.time.LocalDateTime localDateTime1 = null;
        java.time.LocalDateTime localDateTime2 = null;
        model.Reservation reservation5 = new model.Reservation(0, localDateTime1, localDateTime2, 2, 10);
        reservation5.setId((int) (short) 1);
        reservation5.setUserId((int) (short) 1);
        java.time.LocalDateTime localDateTime10 = reservation5.getStartTime();
        org.junit.Assert.assertNull(localDateTime10);
    }

    @Test
    public void test156() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test156");
        java.time.LocalDateTime localDateTime1 = null;
        java.time.LocalDateTime localDateTime2 = null;
        model.Reservation reservation5 = new model.Reservation(0, localDateTime1, localDateTime2, 2, 10);
        reservation5.setId((int) (short) 1);
        java.time.LocalDateTime localDateTime8 = reservation5.getStartTime();
        int int9 = reservation5.getEquipmentId();
        int int10 = reservation5.getUserId();
        model.Equipment equipment15 = new model.Equipment((int) (short) 1, "hi!", "hi!", "");
        model.Sensor sensor16 = new model.Sensor(equipment15);
        model.enums.EquipmentStatus equipmentStatus17 = equipment15.getStatus();
        model.Sensor sensor18 = new model.Sensor(equipment15);
        reservation5.update(sensor18, "");
        model.Equipment equipment25 = new model.Equipment((int) (short) 1, "hi!", "hi!", "");
        model.Sensor sensor26 = new model.Sensor(equipment25);
        model.enums.EquipmentStatus equipmentStatus27 = equipment25.getStatus();
        model.Sensor sensor28 = new model.Sensor(equipment25);
        model.enums.EquipmentStatus equipmentStatus29 = model.enums.EquipmentStatus.ENABLED;
        equipment25.setStatus(equipmentStatus29);
        model.Equipment equipment35 = new model.Equipment((int) (short) 1, "hi!", "hi!", "");
        equipment35.setName("");
        model.enums.EquipmentStatus equipmentStatus38 = model.enums.EquipmentStatus.ENABLED;
        equipment35.setStatus(equipmentStatus38);
        model.Sensor sensor40 = new model.Sensor(equipment35);
        equipment25.update(sensor40, "hi!");
        sensor18.attach((model.SensorObserver) equipment25);
        model.enums.EquipmentStatus equipmentStatus44 = equipment25.getStatus();
        java.lang.String str45 = equipment25.getDescription();
        org.junit.Assert.assertNull(localDateTime8);
        org.junit.Assert.assertTrue("'" + int9 + "' != '" + 10 + "'", int9 == 10);
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 2 + "'", int10 == 2);
        org.junit.Assert.assertTrue("'" + equipmentStatus17 + "' != '" + model.enums.EquipmentStatus.ENABLED + "'", equipmentStatus17.equals(model.enums.EquipmentStatus.ENABLED));
        org.junit.Assert.assertTrue("'" + equipmentStatus27 + "' != '" + model.enums.EquipmentStatus.ENABLED + "'", equipmentStatus27.equals(model.enums.EquipmentStatus.ENABLED));
        org.junit.Assert.assertTrue("'" + equipmentStatus29 + "' != '" + model.enums.EquipmentStatus.ENABLED + "'", equipmentStatus29.equals(model.enums.EquipmentStatus.ENABLED));
        org.junit.Assert.assertTrue("'" + equipmentStatus38 + "' != '" + model.enums.EquipmentStatus.ENABLED + "'", equipmentStatus38.equals(model.enums.EquipmentStatus.ENABLED));
        org.junit.Assert.assertTrue("'" + equipmentStatus44 + "' != '" + model.enums.EquipmentStatus.ENABLED + "'", equipmentStatus44.equals(model.enums.EquipmentStatus.ENABLED));
        org.junit.Assert.assertEquals("'" + str45 + "' != '" + "hi!" + "'", str45, "hi!");
    }

    @Test
    public void test157() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test157");
        model.userhierarchy.HeadLabCoordinator headLabCoordinator0 = model.userhierarchy.HeadLabCoordinator.getInstance();
        boolean boolean1 = headLabCoordinator0.getIsDepartmentApproved();
        int int2 = headLabCoordinator0.getHourlyRate();
        int int3 = headLabCoordinator0.getId();
        org.junit.Assert.assertNotNull(headLabCoordinator0);
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + true + "'", boolean1 == true);
        org.junit.Assert.assertTrue("'" + int2 + "' != '" + 0 + "'", int2 == 0);
        org.junit.Assert.assertTrue("'" + int3 + "' != '" + 1 + "'", int3 == 1);
    }

    @Test
    public void test158() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test158");
        java.time.LocalDateTime localDateTime1 = null;
        java.time.LocalDateTime localDateTime2 = null;
        model.Reservation reservation5 = new model.Reservation(0, localDateTime1, localDateTime2, 2, 10);
        reservation5.setId((int) (short) 1);
        java.time.LocalDateTime localDateTime8 = reservation5.getStartTime();
        int int9 = reservation5.getEquipmentId();
        int int10 = reservation5.getUserId();
        model.Equipment equipment15 = new model.Equipment((int) (short) 1, "hi!", "hi!", "");
        model.Sensor sensor16 = new model.Sensor(equipment15);
        model.enums.EquipmentStatus equipmentStatus17 = equipment15.getStatus();
        model.Sensor sensor18 = new model.Sensor(equipment15);
        reservation5.update(sensor18, "");
        model.Equipment equipment25 = new model.Equipment((int) (short) 1, "hi!", "hi!", "");
        model.Sensor sensor26 = new model.Sensor(equipment25);
        model.enums.EquipmentStatus equipmentStatus27 = equipment25.getStatus();
        model.Sensor sensor28 = new model.Sensor(equipment25);
        model.enums.EquipmentStatus equipmentStatus29 = model.enums.EquipmentStatus.ENABLED;
        equipment25.setStatus(equipmentStatus29);
        model.Equipment equipment35 = new model.Equipment((int) (short) 1, "hi!", "hi!", "");
        equipment35.setName("");
        model.enums.EquipmentStatus equipmentStatus38 = model.enums.EquipmentStatus.ENABLED;
        equipment35.setStatus(equipmentStatus38);
        model.Sensor sensor40 = new model.Sensor(equipment35);
        equipment25.update(sensor40, "hi!");
        sensor18.attach((model.SensorObserver) equipment25);
        java.lang.Class<?> wildcardClass44 = sensor18.getClass();
        org.junit.Assert.assertNull(localDateTime8);
        org.junit.Assert.assertTrue("'" + int9 + "' != '" + 10 + "'", int9 == 10);
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 2 + "'", int10 == 2);
        org.junit.Assert.assertTrue("'" + equipmentStatus17 + "' != '" + model.enums.EquipmentStatus.ENABLED + "'", equipmentStatus17.equals(model.enums.EquipmentStatus.ENABLED));
        org.junit.Assert.assertTrue("'" + equipmentStatus27 + "' != '" + model.enums.EquipmentStatus.ENABLED + "'", equipmentStatus27.equals(model.enums.EquipmentStatus.ENABLED));
        org.junit.Assert.assertTrue("'" + equipmentStatus29 + "' != '" + model.enums.EquipmentStatus.ENABLED + "'", equipmentStatus29.equals(model.enums.EquipmentStatus.ENABLED));
        org.junit.Assert.assertTrue("'" + equipmentStatus38 + "' != '" + model.enums.EquipmentStatus.ENABLED + "'", equipmentStatus38.equals(model.enums.EquipmentStatus.ENABLED));
        org.junit.Assert.assertNotNull(wildcardClass44);
    }

    @Test
    public void test159() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test159");
        model.userhierarchy.HeadLabCoordinator headLabCoordinator0 = model.userhierarchy.HeadLabCoordinator.getInstance();
        boolean boolean1 = headLabCoordinator0.getIsDepartmentApproved();
        boolean boolean2 = headLabCoordinator0.getIsDepartmentApproved();
        int int3 = headLabCoordinator0.getHourlyRate();
        boolean boolean4 = headLabCoordinator0.getIsDepartmentApproved();
        headLabCoordinator0.setUserId((int) (byte) -1);
        int int7 = headLabCoordinator0.getUserId();
        org.junit.Assert.assertNotNull(headLabCoordinator0);
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + true + "'", boolean1 == true);
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + true + "'", boolean2 == true);
        org.junit.Assert.assertTrue("'" + int3 + "' != '" + 0 + "'", int3 == 0);
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + true + "'", boolean4 == true);
        org.junit.Assert.assertTrue("'" + int7 + "' != '" + (-1) + "'", int7 == (-1));
    }

    @Test
    public void test160() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test160");
        model.exceptions.WeakPasswordException weakPasswordException1 = new model.exceptions.WeakPasswordException("1 hi! hi! model.exceptions.WeakPasswordException: hi! ENABLED");
    }

    @Test
    public void test161() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test161");
        model.userhierarchy.HeadLabCoordinator headLabCoordinator0 = model.userhierarchy.HeadLabCoordinator.getInstance();
        boolean boolean1 = headLabCoordinator0.getIsDepartmentApproved();
        boolean boolean2 = headLabCoordinator0.getIsDepartmentApproved();
        int int3 = headLabCoordinator0.getHourlyRate();
        java.lang.String str4 = headLabCoordinator0.getPassword();
        org.junit.Assert.assertNotNull(headLabCoordinator0);
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + true + "'", boolean1 == true);
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + true + "'", boolean2 == true);
        org.junit.Assert.assertTrue("'" + int3 + "' != '" + 0 + "'", int3 == 0);
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "" + "'", str4, "");
    }

    @Test
    public void test162() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test162");
        model.enums.EquipmentStatus equipmentStatus4 = model.enums.EquipmentStatus.ENABLED;
        model.Equipment equipment5 = new model.Equipment((int) (byte) 0, "hi!", "model.exceptions.WeakPasswordException: hi!", "", equipmentStatus4);
        java.lang.String str6 = equipment5.getLabLocation();
        int int7 = equipment5.getId();
        org.junit.Assert.assertTrue("'" + equipmentStatus4 + "' != '" + model.enums.EquipmentStatus.ENABLED + "'", equipmentStatus4.equals(model.enums.EquipmentStatus.ENABLED));
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "" + "'", str6, "");
        org.junit.Assert.assertTrue("'" + int7 + "' != '" + 0 + "'", int7 == 0);
    }

    @Test
    public void test163() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test163");
        java.time.LocalDateTime localDateTime1 = null;
        java.time.LocalDateTime localDateTime2 = null;
        model.Reservation reservation5 = new model.Reservation(0, localDateTime1, localDateTime2, 2, 10);
        reservation5.setId((int) (short) 1);
        java.time.LocalDateTime localDateTime8 = reservation5.getStartTime();
        reservation5.setEquipmentId((int) ' ');
        java.time.LocalDateTime localDateTime11 = null;
        reservation5.setEndTime(localDateTime11);
        model.enums.ReservationStatus reservationStatus13 = reservation5.getStatus();
        model.enums.ReservationStatus reservationStatus14 = reservation5.getStatus();
        reservation5.setEquipmentId(20);
        org.junit.Assert.assertNull(localDateTime8);
        org.junit.Assert.assertNull(reservationStatus13);
        org.junit.Assert.assertNull(reservationStatus14);
    }

    @Test
    public void test164() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test164");
        model.userhierarchy.Student student5 = new model.userhierarchy.Student((int) (short) 0, "hi!", "hi!", "hi!", 0);
        int int6 = student5.getHourlyRate();
        model.enums.UserType userType7 = student5.getUserType();
        model.enums.UserType userType8 = student5.getUserType();
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 10 + "'", int6 == 10);
        org.junit.Assert.assertTrue("'" + userType7 + "' != '" + model.enums.UserType.STUDENT + "'", userType7.equals(model.enums.UserType.STUDENT));
        org.junit.Assert.assertTrue("'" + userType8 + "' != '" + model.enums.UserType.STUDENT + "'", userType8.equals(model.enums.UserType.STUDENT));
    }

    @Test
    public void test165() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test165");
        java.time.LocalDateTime localDateTime1 = null;
        java.time.LocalDateTime localDateTime2 = null;
        model.Reservation reservation5 = new model.Reservation((int) (byte) -1, localDateTime1, localDateTime2, 2, (int) (byte) 10);
        int int6 = reservation5.getUserId();
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 2 + "'", int6 == 2);
    }

    @Test
    public void test166() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test166");
        model.Checkout checkout0 = new model.Checkout();
        model.paymentclasses.ResearchGrantPayment researchGrantPayment1 = new model.paymentclasses.ResearchGrantPayment();
        researchGrantPayment1.makePayment(4);
        checkout0.setPaymentStrategy((model.paymentclasses.PaymentStrategy) researchGrantPayment1);
        checkout0.makePayment(0);
    }

    @Test
    public void test167() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test167");
        model.Equipment equipment4 = new model.Equipment((int) (short) 1, "hi!", "hi!", "");
        java.lang.String str5 = equipment4.getLabLocation();
        java.lang.String str6 = equipment4.getDescription();
        java.lang.String str7 = equipment4.getDescription();
        org.junit.Assert.assertEquals("'" + str5 + "' != '" + "" + "'", str5, "");
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "hi!" + "'", str6, "hi!");
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + "hi!" + "'", str7, "hi!");
    }

    @Test
    public void test168() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test168");
        model.paymentclasses.ResearchGrantPayment researchGrantPayment0 = new model.paymentclasses.ResearchGrantPayment();
        researchGrantPayment0.makePayment((int) '#');
        researchGrantPayment0.makePayment(0);
    }

    @Test
    public void test169() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test169");
        java.time.LocalDateTime localDateTime1 = null;
        java.time.LocalDateTime localDateTime2 = null;
        model.Reservation reservation5 = new model.Reservation(0, localDateTime1, localDateTime2, 2, 10);
        int int6 = reservation5.getTotalOwed();
        java.time.LocalDateTime localDateTime7 = reservation5.getEndTime();
        int int8 = reservation5.getEquipmentId();
        int int9 = reservation5.getUserId();
        java.time.LocalDateTime localDateTime11 = null;
        java.time.LocalDateTime localDateTime12 = null;
        model.Reservation reservation15 = new model.Reservation(0, localDateTime11, localDateTime12, 2, 10);
        int int16 = reservation15.getTotalOwed();
        java.time.LocalDateTime localDateTime17 = reservation15.getEndTime();
        java.time.LocalDateTime localDateTime18 = reservation15.getStartTime();
        model.Equipment equipment23 = new model.Equipment((int) (short) 1, "hi!", "hi!", "");
        model.Sensor sensor24 = new model.Sensor(equipment23);
        model.enums.EquipmentStatus equipmentStatus25 = equipment23.getStatus();
        model.Sensor sensor26 = new model.Sensor(equipment23);
        reservation15.update(sensor26, "model.exceptions.WeakPasswordException: hi!");
        reservation5.update(sensor26, "");
        java.time.LocalDateTime localDateTime31 = reservation5.getEndTime();
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 0 + "'", int6 == 0);
        org.junit.Assert.assertNull(localDateTime7);
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 10 + "'", int8 == 10);
        org.junit.Assert.assertTrue("'" + int9 + "' != '" + 2 + "'", int9 == 2);
        org.junit.Assert.assertTrue("'" + int16 + "' != '" + 0 + "'", int16 == 0);
        org.junit.Assert.assertNull(localDateTime17);
        org.junit.Assert.assertNull(localDateTime18);
        org.junit.Assert.assertTrue("'" + equipmentStatus25 + "' != '" + model.enums.EquipmentStatus.ENABLED + "'", equipmentStatus25.equals(model.enums.EquipmentStatus.ENABLED));
        org.junit.Assert.assertNull(localDateTime31);
    }

    @Test
    public void test170() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test170");
        model.userhierarchy.Researcher researcher5 = new model.userhierarchy.Researcher(11, "model.exceptions.EmailNotUniqueException: ", "model.exceptions.EmailNotUniqueException: ", "1 model.exceptions.WeakPasswordException: hi! hi!  ENABLED", 8);
    }

    @Test
    public void test171() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test171");
        model.userhierarchy.LabManager labManager5 = new model.userhierarchy.LabManager((-1), "model.exceptions.WeakPasswordException: hi!", "hi!", "1 hi! model.exceptions.EmailNotUniqueException:   ENABLED", (int) (byte) 10);
    }

    @Test
    public void test172() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test172");
        model.Equipment equipment4 = new model.Equipment((int) (short) 1, "hi!", "hi!", "");
        equipment4.setName("");
        equipment4.setDescription("hi!");
        model.Sensor sensor9 = equipment4.getSensor();
        int int10 = equipment4.getId();
        org.junit.Assert.assertNotNull(sensor9);
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 1 + "'", int10 == 1);
    }

    @Test
    public void test173() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test173");
        model.Checkout checkout0 = new model.Checkout();
        model.paymentclasses.ResearchGrantPayment researchGrantPayment1 = new model.paymentclasses.ResearchGrantPayment();
        researchGrantPayment1.makePayment(4);
        checkout0.setPaymentStrategy((model.paymentclasses.PaymentStrategy) researchGrantPayment1);
        java.lang.Class<?> wildcardClass5 = researchGrantPayment1.getClass();
        org.junit.Assert.assertNotNull(wildcardClass5);
    }

    @Test
    public void test174() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test174");
        model.userhierarchy.LabManager labManager5 = new model.userhierarchy.LabManager((int) '4', "model.exceptions.EmailNotUniqueException: ", "hi!", "hi!", (int) (short) 10);
        boolean boolean6 = labManager5.getIsDepartmentApproved();
        int int7 = labManager5.getHourlyRate();
        int int8 = labManager5.getHourlyRate();
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertTrue("'" + int7 + "' != '" + 0 + "'", int7 == 0);
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 0 + "'", int8 == 0);
    }

    @Test
    public void test175() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test175");
        model.Equipment equipment4 = new model.Equipment((int) (short) 1, "hi!", "hi!", "");
        equipment4.setName("");
        equipment4.setDescription("hi!");
        model.Sensor sensor9 = equipment4.getSensor();
        model.Equipment equipment10 = sensor9.getEquipment();
        org.junit.Assert.assertNotNull(sensor9);
        org.junit.Assert.assertNotNull(equipment10);
    }

    @Test
    public void test176() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test176");
        model.userhierarchy.Student student5 = new model.userhierarchy.Student(38, "", "hi!", "1 hi! hi! model.exceptions.WeakPasswordException: hi! ENABLED", (-1));
    }

    @Test
    public void test177() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test177");
        model.paymentclasses.CreditPayment creditPayment0 = new model.paymentclasses.CreditPayment();
        creditPayment0.makePayment((int) (short) -1);
        creditPayment0.makePayment((-1));
    }

    @Test
    public void test178() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test178");
        java.time.LocalDateTime localDateTime1 = null;
        java.time.LocalDateTime localDateTime2 = null;
        model.Reservation reservation5 = new model.Reservation(0, localDateTime1, localDateTime2, 2, 10);
        int int6 = reservation5.getTotalOwed();
        reservation5.setTotalOwed((int) (byte) 100);
        int int9 = reservation5.getTotalOwed();
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 0 + "'", int6 == 0);
        org.junit.Assert.assertTrue("'" + int9 + "' != '" + 100 + "'", int9 == 100);
    }

    @Test
    public void test179() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test179");
        model.userhierarchy.HeadLabCoordinator headLabCoordinator0 = model.userhierarchy.HeadLabCoordinator.getInstance();
        boolean boolean1 = headLabCoordinator0.getIsDepartmentApproved();
        boolean boolean2 = headLabCoordinator0.getIsDepartmentApproved();
        headLabCoordinator0.setUserId((int) (byte) 1);
        int int5 = headLabCoordinator0.getUserId();
        int int6 = headLabCoordinator0.getVerificationNum();
        org.junit.Assert.assertNotNull(headLabCoordinator0);
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + true + "'", boolean1 == true);
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + true + "'", boolean2 == true);
        org.junit.Assert.assertTrue("'" + int5 + "' != '" + 1 + "'", int5 == 1);
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 8 + "'", int6 == 8);
    }

    @Test
    public void test180() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test180");
        model.Equipment equipment4 = new model.Equipment((int) (short) 1, "hi!", "hi!", "");
        java.lang.String str5 = equipment4.getName();
        equipment4.setName("model.exceptions.WeakPasswordException: hi!");
        model.Equipment equipment12 = new model.Equipment((int) (short) 1, "hi!", "hi!", "");
        java.lang.String str13 = equipment12.getLabLocation();
        model.Sensor sensor14 = equipment12.getSensor();
        sensor14.notifyObservers("");
        equipment4.update(sensor14, "1 hi! hi! model.exceptions.WeakPasswordException: hi! ENABLED");
        org.junit.Assert.assertEquals("'" + str5 + "' != '" + "hi!" + "'", str5, "hi!");
        org.junit.Assert.assertEquals("'" + str13 + "' != '" + "" + "'", str13, "");
        org.junit.Assert.assertNotNull(sensor14);
    }

    @Test
    public void test181() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test181");
        model.userhierarchy.HeadLabCoordinator headLabCoordinator0 = model.userhierarchy.HeadLabCoordinator.getInstance();
        boolean boolean1 = headLabCoordinator0.getIsDepartmentApproved();
        int int2 = headLabCoordinator0.getHourlyRate();
        int int3 = headLabCoordinator0.getHourlyRate();
        java.lang.String str4 = headLabCoordinator0.getEmail();
        boolean boolean5 = headLabCoordinator0.getIsDepartmentApproved();
        org.junit.Assert.assertNotNull(headLabCoordinator0);
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + true + "'", boolean1 == true);
        org.junit.Assert.assertTrue("'" + int2 + "' != '" + 0 + "'", int2 == 0);
        org.junit.Assert.assertTrue("'" + int3 + "' != '" + 0 + "'", int3 == 0);
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "model.exceptions.WeakPasswordException: hi!" + "'", str4, "model.exceptions.WeakPasswordException: hi!");
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
    }

    @Test
    public void test182() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test182");
        model.userhierarchy.HeadLabCoordinator headLabCoordinator0 = model.userhierarchy.HeadLabCoordinator.getInstance();
        boolean boolean1 = headLabCoordinator0.getIsDepartmentApproved();
        boolean boolean2 = headLabCoordinator0.getIsDepartmentApproved();
        headLabCoordinator0.setUserId((int) (byte) 1);
        headLabCoordinator0.initialize((int) (byte) 100, "", "hi!", "model.exceptions.WeakPasswordException: hi!", (int) (short) 100);
        headLabCoordinator0.setUniversityAfilliated(false);
        org.junit.Assert.assertNotNull(headLabCoordinator0);
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + true + "'", boolean1 == true);
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + true + "'", boolean2 == true);
    }

    @Test
    public void test183() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test183");
        java.time.LocalDateTime localDateTime1 = null;
        java.time.LocalDateTime localDateTime2 = null;
        model.Reservation reservation5 = new model.Reservation(0, localDateTime1, localDateTime2, 2, 10);
        int int6 = reservation5.getTotalOwed();
        reservation5.setTotalOwed((int) (byte) 100);
        reservation5.setUserId((int) (byte) 10);
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 0 + "'", int6 == 0);
    }

    @Test
    public void test184() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test184");
        model.userhierarchy.Faculty faculty5 = new model.userhierarchy.Faculty((int) (short) -1, "", "", "model.exceptions.EmailNotUniqueException: ", (int) (byte) 10);
        int int6 = faculty5.getVerificationNum();
        int int7 = faculty5.getId();
        int int8 = faculty5.getHourlyRate();
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 10 + "'", int6 == 10);
        org.junit.Assert.assertTrue("'" + int7 + "' != '" + (-1) + "'", int7 == (-1));
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 15 + "'", int8 == 15);
    }

    @Test
    public void test185() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test185");
        model.IdGeneration idGeneration0 = model.IdGeneration.getInstance();
        int int1 = idGeneration0.nextUserId();
        idGeneration0.setEquipmentIdCount((int) (short) 10);
        int int4 = idGeneration0.nextReservationId();
        int int5 = idGeneration0.nextReservationId();
        int int6 = idGeneration0.nextEquipmentId();
        idGeneration0.setReservationIdCount(5);
        org.junit.Assert.assertNotNull(idGeneration0);
        org.junit.Assert.assertTrue("'" + int1 + "' != '" + 3 + "'", int1 == 3);
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 22 + "'", int4 == 22);
        org.junit.Assert.assertTrue("'" + int5 + "' != '" + 23 + "'", int5 == 23);
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 10 + "'", int6 == 10);
    }

    @Test
    public void test186() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test186");
        model.userhierarchy.Researcher researcher5 = new model.userhierarchy.Researcher(38, "1 hi! hi! model.exceptions.WeakPasswordException: hi! ENABLED", "model.exceptions.WeakPasswordException: model.exceptions.WeakPasswordException: hi!", "model.exceptions.EmailNotUniqueException: ", (int) (short) 100);
    }

    @Test
    public void test187() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test187");
        java.time.LocalDateTime localDateTime1 = null;
        java.time.LocalDateTime localDateTime2 = null;
        model.Reservation reservation5 = new model.Reservation((int) (byte) -1, localDateTime1, localDateTime2, 2, (int) (byte) 10);
        java.time.LocalDateTime localDateTime6 = reservation5.getEndTime();
        org.junit.Assert.assertNull(localDateTime6);
    }

    @Test
    public void test188() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test188");
        java.time.LocalDateTime localDateTime1 = null;
        java.time.LocalDateTime localDateTime2 = null;
        model.Reservation reservation5 = new model.Reservation(0, localDateTime1, localDateTime2, 2, 10);
        reservation5.setId((int) (short) 1);
        java.time.LocalDateTime localDateTime8 = reservation5.getStartTime();
        reservation5.setUserId((int) 'a');
        java.time.LocalDateTime localDateTime12 = null;
        java.time.LocalDateTime localDateTime13 = null;
        model.Reservation reservation16 = new model.Reservation(0, localDateTime12, localDateTime13, 2, 10);
        reservation16.setId((int) (short) 1);
        java.time.LocalDateTime localDateTime19 = reservation16.getStartTime();
        reservation16.setEquipmentId((int) ' ');
        java.time.LocalDateTime localDateTime22 = null;
        reservation16.setEndTime(localDateTime22);
        model.enums.ReservationStatus reservationStatus24 = reservation16.getStatus();
        int int25 = reservation16.getId();
        model.enums.ReservationStatus reservationStatus26 = model.enums.ReservationStatus.CANCELLED;
        reservation16.setStatus(reservationStatus26);
        reservation5.setStatus(reservationStatus26);
        org.junit.Assert.assertNull(localDateTime8);
        org.junit.Assert.assertNull(localDateTime19);
        org.junit.Assert.assertNull(reservationStatus24);
        org.junit.Assert.assertTrue("'" + int25 + "' != '" + 1 + "'", int25 == 1);
        org.junit.Assert.assertTrue("'" + reservationStatus26 + "' != '" + model.enums.ReservationStatus.CANCELLED + "'", reservationStatus26.equals(model.enums.ReservationStatus.CANCELLED));
    }

    @Test
    public void test189() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test189");
        java.time.LocalDateTime localDateTime1 = null;
        java.time.LocalDateTime localDateTime2 = null;
        model.Reservation reservation5 = new model.Reservation(0, localDateTime1, localDateTime2, 2, 10);
        int int6 = reservation5.getTotalOwed();
        java.time.LocalDateTime localDateTime7 = null;
        reservation5.setStartTime(localDateTime7);
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 0 + "'", int6 == 0);
    }

    @Test
    public void test190() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test190");
        model.Equipment equipment4 = new model.Equipment((int) (short) 1, "hi!", "hi!", "");
        model.Sensor sensor5 = new model.Sensor(equipment4);
        model.enums.EquipmentStatus equipmentStatus6 = equipment4.getStatus();
        model.Sensor sensor7 = new model.Sensor(equipment4);
        model.enums.EquipmentStatus equipmentStatus8 = model.enums.EquipmentStatus.ENABLED;
        equipment4.setStatus(equipmentStatus8);
        model.Equipment equipment14 = new model.Equipment((int) (short) 1, "hi!", "hi!", "");
        equipment14.setName("");
        model.enums.EquipmentStatus equipmentStatus17 = model.enums.EquipmentStatus.ENABLED;
        equipment14.setStatus(equipmentStatus17);
        model.Sensor sensor19 = new model.Sensor(equipment14);
        equipment4.update(sensor19, "hi!");
        model.Equipment equipment26 = new model.Equipment((int) (short) 1, "hi!", "hi!", "");
        model.Sensor sensor27 = new model.Sensor(equipment26);
        model.enums.EquipmentStatus equipmentStatus28 = equipment26.getStatus();
        model.Sensor sensor29 = new model.Sensor(equipment26);
        equipment26.setName("hi!");
        sensor19.detach((model.SensorObserver) equipment26);
        java.time.LocalDateTime localDateTime34 = null;
        java.time.LocalDateTime localDateTime35 = null;
        model.Reservation reservation38 = new model.Reservation(0, localDateTime34, localDateTime35, 2, 10);
        reservation38.setId((int) (short) 1);
        java.time.LocalDateTime localDateTime41 = reservation38.getStartTime();
        int int42 = reservation38.getEquipmentId();
        int int43 = reservation38.getUserId();
        int int44 = reservation38.getTotalOwed();
        java.time.LocalDateTime localDateTime45 = reservation38.getEndTime();
        model.Equipment equipment50 = new model.Equipment((int) (short) 1, "hi!", "hi!", "");
        java.lang.String str51 = equipment50.getLabLocation();
        java.lang.String str52 = equipment50.getLabLocation();
        java.lang.String str53 = equipment50.getName();
        model.Sensor sensor54 = equipment50.getSensor();
        reservation38.update(sensor54, "model.exceptions.WeakPasswordException: hi!");
        java.time.LocalDateTime localDateTime57 = reservation38.getStartTime();
        java.time.LocalDateTime localDateTime58 = reservation38.getStartTime();
        sensor19.detach((model.SensorObserver) reservation38);
        org.junit.Assert.assertTrue("'" + equipmentStatus6 + "' != '" + model.enums.EquipmentStatus.ENABLED + "'", equipmentStatus6.equals(model.enums.EquipmentStatus.ENABLED));
        org.junit.Assert.assertTrue("'" + equipmentStatus8 + "' != '" + model.enums.EquipmentStatus.ENABLED + "'", equipmentStatus8.equals(model.enums.EquipmentStatus.ENABLED));
        org.junit.Assert.assertTrue("'" + equipmentStatus17 + "' != '" + model.enums.EquipmentStatus.ENABLED + "'", equipmentStatus17.equals(model.enums.EquipmentStatus.ENABLED));
        org.junit.Assert.assertTrue("'" + equipmentStatus28 + "' != '" + model.enums.EquipmentStatus.ENABLED + "'", equipmentStatus28.equals(model.enums.EquipmentStatus.ENABLED));
        org.junit.Assert.assertNull(localDateTime41);
        org.junit.Assert.assertTrue("'" + int42 + "' != '" + 10 + "'", int42 == 10);
        org.junit.Assert.assertTrue("'" + int43 + "' != '" + 2 + "'", int43 == 2);
        org.junit.Assert.assertTrue("'" + int44 + "' != '" + 0 + "'", int44 == 0);
        org.junit.Assert.assertNull(localDateTime45);
        org.junit.Assert.assertEquals("'" + str51 + "' != '" + "" + "'", str51, "");
        org.junit.Assert.assertEquals("'" + str52 + "' != '" + "" + "'", str52, "");
        org.junit.Assert.assertEquals("'" + str53 + "' != '" + "hi!" + "'", str53, "hi!");
        org.junit.Assert.assertNotNull(sensor54);
        org.junit.Assert.assertNull(localDateTime57);
        org.junit.Assert.assertNull(localDateTime58);
    }

    @Test
    public void test191() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test191");
        model.userhierarchy.HeadLabCoordinator headLabCoordinator0 = model.userhierarchy.HeadLabCoordinator.getInstance();
        boolean boolean1 = headLabCoordinator0.getIsDepartmentApproved();
        int int2 = headLabCoordinator0.getHourlyRate();
        int int3 = headLabCoordinator0.getHourlyRate();
        int int4 = headLabCoordinator0.getHourlyRate();
        headLabCoordinator0.setUserId(8);
        org.junit.Assert.assertNotNull(headLabCoordinator0);
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + true + "'", boolean1 == true);
        org.junit.Assert.assertTrue("'" + int2 + "' != '" + 0 + "'", int2 == 0);
        org.junit.Assert.assertTrue("'" + int3 + "' != '" + 0 + "'", int3 == 0);
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 0 + "'", int4 == 0);
    }

    @Test
    public void test192() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test192");
        model.Equipment equipment4 = new model.Equipment((int) (short) 1, "hi!", "hi!", "");
        equipment4.setName("");
        equipment4.setDescription("hi!");
        model.Sensor sensor9 = equipment4.getSensor();
        java.lang.String str10 = equipment4.getName();
        org.junit.Assert.assertNotNull(sensor9);
        org.junit.Assert.assertEquals("'" + str10 + "' != '" + "" + "'", str10, "");
    }

    @Test
    public void test193() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test193");
        model.userhierarchy.LabManager labManager5 = new model.userhierarchy.LabManager((int) '#', "1 hi! model.exceptions.EmailNotUniqueException:   ENABLED", "model.exceptions.WeakPasswordException: hi!", "model.exceptions.WeakPasswordException: model.exceptions.WeakPasswordException: hi!", 0);
    }

    @Test
    public void test194() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test194");
        model.UserFactory userFactory0 = new model.UserFactory();
        model.enums.UserType userType6 = model.enums.UserType.RESEARCHER;
        model.userhierarchy.User user7 = userFactory0.createUser((int) (byte) 100, "hi!", "model.exceptions.WeakPasswordException: model.exceptions.WeakPasswordException: hi!", "", 100, userType6);
        model.enums.UserType userType12 = model.enums.UserType.HEADLABCOORDINATOR;
        model.userhierarchy.User user13 = userFactory0.createUser("1 hi! hi! model.exceptions.WeakPasswordException: hi! ENABLED", "1 hi! hi! model.exceptions.WeakPasswordException: hi! ENABLED", "1 model.exceptions.WeakPasswordException: hi! hi!  ENABLED", (int) '#', userType12);
        org.junit.Assert.assertTrue("'" + userType6 + "' != '" + model.enums.UserType.RESEARCHER + "'", userType6.equals(model.enums.UserType.RESEARCHER));
        org.junit.Assert.assertNotNull(user7);
        org.junit.Assert.assertTrue("'" + userType12 + "' != '" + model.enums.UserType.HEADLABCOORDINATOR + "'", userType12.equals(model.enums.UserType.HEADLABCOORDINATOR));
        org.junit.Assert.assertNotNull(user13);
    }

    @Test
    public void test195() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test195");
        java.time.LocalDateTime localDateTime1 = null;
        java.time.LocalDateTime localDateTime2 = null;
        model.Reservation reservation5 = new model.Reservation(0, localDateTime1, localDateTime2, 2, 10);
        int int6 = reservation5.getTotalOwed();
        java.time.LocalDateTime localDateTime7 = reservation5.getEndTime();
        int int8 = reservation5.getEquipmentId();
        int int9 = reservation5.getUserId();
        java.time.LocalDateTime localDateTime11 = null;
        java.time.LocalDateTime localDateTime12 = null;
        model.Reservation reservation15 = new model.Reservation(0, localDateTime11, localDateTime12, 2, 10);
        int int16 = reservation15.getTotalOwed();
        java.time.LocalDateTime localDateTime17 = reservation15.getEndTime();
        java.time.LocalDateTime localDateTime18 = reservation15.getStartTime();
        model.Equipment equipment23 = new model.Equipment((int) (short) 1, "hi!", "hi!", "");
        model.Sensor sensor24 = new model.Sensor(equipment23);
        model.enums.EquipmentStatus equipmentStatus25 = equipment23.getStatus();
        model.Sensor sensor26 = new model.Sensor(equipment23);
        reservation15.update(sensor26, "model.exceptions.WeakPasswordException: hi!");
        reservation5.update(sensor26, "");
        model.Equipment equipment31 = sensor26.getEquipment();
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 0 + "'", int6 == 0);
        org.junit.Assert.assertNull(localDateTime7);
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 10 + "'", int8 == 10);
        org.junit.Assert.assertTrue("'" + int9 + "' != '" + 2 + "'", int9 == 2);
        org.junit.Assert.assertTrue("'" + int16 + "' != '" + 0 + "'", int16 == 0);
        org.junit.Assert.assertNull(localDateTime17);
        org.junit.Assert.assertNull(localDateTime18);
        org.junit.Assert.assertTrue("'" + equipmentStatus25 + "' != '" + model.enums.EquipmentStatus.ENABLED + "'", equipmentStatus25.equals(model.enums.EquipmentStatus.ENABLED));
        org.junit.Assert.assertNotNull(equipment31);
    }

    @Test
    public void test196() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test196");
        java.time.LocalDateTime localDateTime1 = null;
        java.time.LocalDateTime localDateTime2 = null;
        model.Reservation reservation5 = new model.Reservation(0, localDateTime1, localDateTime2, 2, 10);
        reservation5.setId((int) (short) 1);
        java.time.LocalDateTime localDateTime8 = reservation5.getStartTime();
        reservation5.setEquipmentId((int) ' ');
        java.time.LocalDateTime localDateTime11 = null;
        reservation5.setEndTime(localDateTime11);
        model.enums.ReservationStatus reservationStatus13 = reservation5.getStatus();
        model.enums.ReservationStatus reservationStatus14 = reservation5.getStatus();
        java.time.LocalDateTime localDateTime15 = null;
        reservation5.setEndTime(localDateTime15);
        org.junit.Assert.assertNull(localDateTime8);
        org.junit.Assert.assertNull(reservationStatus13);
        org.junit.Assert.assertNull(reservationStatus14);
    }

    @Test
    public void test197() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test197");
        model.Equipment equipment4 = new model.Equipment((-1), "model.exceptions.EmailNotUniqueException: ", "model.exceptions.WeakPasswordException: model.exceptions.WeakPasswordException: hi!", "1 hi! hi! model.exceptions.WeakPasswordException: hi! ENABLED");
    }

    @Test
    public void test198() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test198");
        model.enums.EquipmentStatus equipmentStatus8 = model.enums.EquipmentStatus.MAINTENANCE;
        model.Equipment equipment9 = new model.Equipment(10, "model.exceptions.WeakPasswordException: hi!", "model.exceptions.WeakPasswordException: model.exceptions.WeakPasswordException: hi!", "1  hi!  ENABLED", equipmentStatus8);
        model.Equipment equipment10 = new model.Equipment(11, "hi!", "model.exceptions.WeakPasswordException: hi!", "1 model.exceptions.WeakPasswordException: hi! hi!  ENABLED", equipmentStatus8);
        org.junit.Assert.assertTrue("'" + equipmentStatus8 + "' != '" + model.enums.EquipmentStatus.MAINTENANCE + "'", equipmentStatus8.equals(model.enums.EquipmentStatus.MAINTENANCE));
    }

    @Test
    public void test199() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test199");
        model.userhierarchy.Faculty faculty5 = new model.userhierarchy.Faculty(4, "1 hi! hi! model.exceptions.WeakPasswordException: hi! ENABLED", "model.exceptions.EmailNotUniqueException: ", "1  hi!  ENABLED", 3);
        int int6 = faculty5.getHourlyRate();
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 15 + "'", int6 == 15);
    }

    @Test
    public void test200() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test200");
        model.Equipment equipment4 = new model.Equipment((int) (short) 1, "hi!", "hi!", "");
        java.lang.String str5 = equipment4.getLabLocation();
        model.Sensor sensor6 = equipment4.getSensor();
        sensor6.notifyObservers("");
        model.Equipment equipment13 = new model.Equipment((int) (short) 1, "hi!", "hi!", "");
        java.lang.String str14 = equipment13.getLabLocation();
        model.Sensor sensor15 = equipment13.getSensor();
        sensor6.detach((model.SensorObserver) equipment13);
        model.Equipment equipment17 = sensor6.getEquipment();
        equipment17.setName("");
        org.junit.Assert.assertEquals("'" + str5 + "' != '" + "" + "'", str5, "");
        org.junit.Assert.assertNotNull(sensor6);
        org.junit.Assert.assertEquals("'" + str14 + "' != '" + "" + "'", str14, "");
        org.junit.Assert.assertNotNull(sensor15);
        org.junit.Assert.assertNotNull(equipment17);
    }

    @Test
    public void test201() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test201");
        model.Equipment equipment4 = new model.Equipment((int) (short) 1, "hi!", "hi!", "");
        java.lang.String str5 = equipment4.getLabLocation();
        model.Sensor sensor6 = equipment4.getSensor();
        sensor6.notifyObservers("");
        model.Equipment equipment9 = sensor6.getEquipment();
        sensor6.departure();
        org.junit.Assert.assertEquals("'" + str5 + "' != '" + "" + "'", str5, "");
        org.junit.Assert.assertNotNull(sensor6);
        org.junit.Assert.assertNotNull(equipment9);
    }

    @Test
    public void test202() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test202");
        model.exceptions.EmailNotUniqueException emailNotUniqueException1 = new model.exceptions.EmailNotUniqueException("");
        model.exceptions.EmailNotUniqueException emailNotUniqueException3 = new model.exceptions.EmailNotUniqueException("");
        emailNotUniqueException1.addSuppressed((java.lang.Throwable) emailNotUniqueException3);
        java.lang.Throwable[] throwableArray5 = emailNotUniqueException1.getSuppressed();
        model.exceptions.EmailNotUniqueException emailNotUniqueException7 = new model.exceptions.EmailNotUniqueException("");
        model.exceptions.EmailNotUniqueException emailNotUniqueException9 = new model.exceptions.EmailNotUniqueException("");
        emailNotUniqueException7.addSuppressed((java.lang.Throwable) emailNotUniqueException9);
        java.lang.Throwable[] throwableArray11 = emailNotUniqueException7.getSuppressed();
        emailNotUniqueException1.addSuppressed((java.lang.Throwable) emailNotUniqueException7);
        java.lang.Throwable[] throwableArray13 = emailNotUniqueException1.getSuppressed();
        org.junit.Assert.assertNotNull(throwableArray5);
        org.junit.Assert.assertNotNull(throwableArray11);
        org.junit.Assert.assertNotNull(throwableArray13);
    }

    @Test
    public void test203() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test203");
        model.exceptions.WeakPasswordException weakPasswordException1 = new model.exceptions.WeakPasswordException("model.exceptions.EmailNotUniqueException: ");
    }

    @Test
    public void test204() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test204");
        model.userhierarchy.LabManager labManager5 = new model.userhierarchy.LabManager((int) '4', "model.exceptions.EmailNotUniqueException: ", "hi!", "hi!", (int) (short) 10);
        boolean boolean6 = labManager5.getIsDepartmentApproved();
        int int7 = labManager5.getHourlyRate();
        java.lang.String str8 = labManager5.getPassword();
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertTrue("'" + int7 + "' != '" + 0 + "'", int7 == 0);
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "hi!" + "'", str8, "hi!");
    }

    @Test
    public void test205() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test205");
        java.time.LocalDateTime localDateTime1 = null;
        java.time.LocalDateTime localDateTime2 = null;
        model.Reservation reservation5 = new model.Reservation(0, localDateTime1, localDateTime2, 2, 10);
        reservation5.setId((int) (short) 1);
        java.time.LocalDateTime localDateTime8 = reservation5.getStartTime();
        int int9 = reservation5.getEquipmentId();
        java.time.LocalDateTime localDateTime10 = null;
        reservation5.setStartTime(localDateTime10);
        java.time.LocalDateTime localDateTime12 = null;
        reservation5.setEndTime(localDateTime12);
        reservation5.setUserId((int) (byte) 0);
        reservation5.setTotalOwed(0);
        reservation5.setTotalOwed(13);
        org.junit.Assert.assertNull(localDateTime8);
        org.junit.Assert.assertTrue("'" + int9 + "' != '" + 10 + "'", int9 == 10);
    }

    @Test
    public void test206() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test206");
        java.time.LocalDateTime localDateTime1 = null;
        java.time.LocalDateTime localDateTime2 = null;
        model.Reservation reservation5 = new model.Reservation(0, localDateTime1, localDateTime2, 2, 10);
        int int6 = reservation5.getTotalOwed();
        java.time.LocalDateTime localDateTime7 = reservation5.getEndTime();
        java.time.LocalDateTime localDateTime8 = reservation5.getStartTime();
        java.time.LocalDateTime localDateTime9 = reservation5.getStartTime();
        reservation5.setDepositAmount(52);
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 0 + "'", int6 == 0);
        org.junit.Assert.assertNull(localDateTime7);
        org.junit.Assert.assertNull(localDateTime8);
        org.junit.Assert.assertNull(localDateTime9);
    }

    @Test
    public void test207() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test207");
        model.Checkout checkout0 = new model.Checkout();
        model.paymentclasses.ResearchGrantPayment researchGrantPayment1 = new model.paymentclasses.ResearchGrantPayment();
        researchGrantPayment1.makePayment(4);
        checkout0.setPaymentStrategy((model.paymentclasses.PaymentStrategy) researchGrantPayment1);
        checkout0.makePayment(9);
        model.paymentclasses.InstitutionalGrantPayment institutionalGrantPayment7 = new model.paymentclasses.InstitutionalGrantPayment();
        institutionalGrantPayment7.makePayment((int) (short) 0);
        checkout0.setPaymentStrategy((model.paymentclasses.PaymentStrategy) institutionalGrantPayment7);
    }

    @Test
    public void test208() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test208");
        model.Equipment equipment4 = new model.Equipment((int) (short) 1, "hi!", "hi!", "");
        model.Sensor sensor5 = new model.Sensor(equipment4);
        model.enums.EquipmentStatus equipmentStatus6 = equipment4.getStatus();
        model.Sensor sensor7 = new model.Sensor(equipment4);
        model.enums.EquipmentStatus equipmentStatus8 = model.enums.EquipmentStatus.ENABLED;
        equipment4.setStatus(equipmentStatus8);
        equipment4.setLabLocation("hi!");
        equipment4.setName("hi!");
        java.lang.String str14 = equipment4.toString();
        org.junit.Assert.assertTrue("'" + equipmentStatus6 + "' != '" + model.enums.EquipmentStatus.ENABLED + "'", equipmentStatus6.equals(model.enums.EquipmentStatus.ENABLED));
        org.junit.Assert.assertTrue("'" + equipmentStatus8 + "' != '" + model.enums.EquipmentStatus.ENABLED + "'", equipmentStatus8.equals(model.enums.EquipmentStatus.ENABLED));
        org.junit.Assert.assertEquals("'" + str14 + "' != '" + "1 hi! hi! hi! ENABLED" + "'", str14, "1 hi! hi! hi! ENABLED");
    }

    @Test
    public void test209() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test209");
        model.IdGeneration idGeneration0 = model.IdGeneration.getInstance();
        int int1 = idGeneration0.nextUserId();
        int int2 = idGeneration0.nextReservationId();
        idGeneration0.setEquipmentIdCount((int) (byte) 0);
        int int5 = idGeneration0.nextEquipmentId();
        int int6 = idGeneration0.nextEquipmentId();
        org.junit.Assert.assertNotNull(idGeneration0);
        org.junit.Assert.assertTrue("'" + int1 + "' != '" + 5 + "'", int1 == 5);
        org.junit.Assert.assertTrue("'" + int2 + "' != '" + 5 + "'", int2 == 5);
        org.junit.Assert.assertTrue("'" + int5 + "' != '" + 0 + "'", int5 == 0);
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 1 + "'", int6 == 1);
    }

    @Test
    public void test210() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test210");
        model.Equipment equipment4 = new model.Equipment(7, "model.exceptions.WeakPasswordException: hi!", "1 hi! hi! hi! ENABLED", "hi!");
    }

    @Test
    public void test211() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test211");
        model.Checkout checkout0 = new model.Checkout();
        model.paymentclasses.ResearchGrantPayment researchGrantPayment1 = new model.paymentclasses.ResearchGrantPayment();
        researchGrantPayment1.makePayment(4);
        checkout0.setPaymentStrategy((model.paymentclasses.PaymentStrategy) researchGrantPayment1);
        checkout0.makePayment(9);
        model.paymentclasses.ResearchGrantPayment researchGrantPayment7 = new model.paymentclasses.ResearchGrantPayment();
        researchGrantPayment7.makePayment(4);
        checkout0.setPaymentStrategy((model.paymentclasses.PaymentStrategy) researchGrantPayment7);
    }

    @Test
    public void test212() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test212");
        model.Department department0 = new model.Department();
        model.userhierarchy.Student student6 = new model.userhierarchy.Student((int) (short) 0, "hi!", "hi!", "hi!", 0);
        int int7 = student6.getHourlyRate();
        student6.setUniversityAfilliated(false);
        model.enums.UserType userType10 = student6.getUserType();
        boolean boolean11 = department0.approveAccount((model.userhierarchy.User) student6);
        model.userhierarchy.HeadLabCoordinator headLabCoordinator12 = model.userhierarchy.HeadLabCoordinator.getInstance();
        java.lang.String str13 = headLabCoordinator12.getPassword();
        int int14 = headLabCoordinator12.getHourlyRate();
        int int15 = headLabCoordinator12.getHourlyRate();
        headLabCoordinator12.initialize((int) (byte) 0, "model.exceptions.WeakPasswordException: hi!", "", "", (int) (short) 10);
        boolean boolean22 = department0.approveAccount((model.userhierarchy.User) headLabCoordinator12);
        headLabCoordinator12.initialize((int) (short) 10, "model.exceptions.EmailNotUniqueException: ", "1  hi!  ENABLED", "", (int) (byte) 1);
        int int29 = headLabCoordinator12.getVerificationNum();
        org.junit.Assert.assertTrue("'" + int7 + "' != '" + 10 + "'", int7 == 10);
        org.junit.Assert.assertTrue("'" + userType10 + "' != '" + model.enums.UserType.STUDENT + "'", userType10.equals(model.enums.UserType.STUDENT));
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + true + "'", boolean11 == true);
        org.junit.Assert.assertNotNull(headLabCoordinator12);
        org.junit.Assert.assertEquals("'" + str13 + "' != '" + "1 model.exceptions.WeakPasswordException: hi! hi!  ENABLED" + "'", str13, "1 model.exceptions.WeakPasswordException: hi! hi!  ENABLED");
        org.junit.Assert.assertTrue("'" + int14 + "' != '" + 0 + "'", int14 == 0);
        org.junit.Assert.assertTrue("'" + int15 + "' != '" + 0 + "'", int15 == 0);
        org.junit.Assert.assertTrue("'" + boolean22 + "' != '" + true + "'", boolean22 == true);
        org.junit.Assert.assertTrue("'" + int29 + "' != '" + 1 + "'", int29 == 1);
    }

    @Test
    public void test213() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test213");
        java.time.LocalDateTime localDateTime1 = null;
        java.time.LocalDateTime localDateTime2 = null;
        model.Reservation reservation5 = new model.Reservation(0, localDateTime1, localDateTime2, 2, 10);
        int int6 = reservation5.getTotalOwed();
        int int7 = reservation5.getEquipmentId();
        java.time.LocalDateTime localDateTime8 = reservation5.getStartTime();
        model.enums.ReservationStatus reservationStatus9 = reservation5.getStatus();
        int int10 = reservation5.getId();
        java.time.LocalDateTime localDateTime11 = null;
        reservation5.setEndTime(localDateTime11);
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 0 + "'", int6 == 0);
        org.junit.Assert.assertTrue("'" + int7 + "' != '" + 10 + "'", int7 == 10);
        org.junit.Assert.assertNull(localDateTime8);
        org.junit.Assert.assertNull(reservationStatus9);
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 0 + "'", int10 == 0);
    }

    @Test
    public void test214() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test214");
        model.Department department0 = new model.Department();
        model.userhierarchy.Student student6 = new model.userhierarchy.Student((int) (short) 0, "hi!", "hi!", "hi!", 0);
        int int7 = student6.getHourlyRate();
        student6.setUniversityAfilliated(false);
        model.enums.UserType userType10 = student6.getUserType();
        boolean boolean11 = department0.approveAccount((model.userhierarchy.User) student6);
        model.userhierarchy.HeadLabCoordinator headLabCoordinator12 = model.userhierarchy.HeadLabCoordinator.getInstance();
        java.lang.String str13 = headLabCoordinator12.getPassword();
        int int14 = headLabCoordinator12.getHourlyRate();
        int int15 = headLabCoordinator12.getHourlyRate();
        headLabCoordinator12.initialize((int) (byte) 0, "model.exceptions.WeakPasswordException: hi!", "", "", (int) (short) 10);
        boolean boolean22 = department0.approveAccount((model.userhierarchy.User) headLabCoordinator12);
        model.userhierarchy.HeadLabCoordinator headLabCoordinator23 = model.userhierarchy.HeadLabCoordinator.getInstance();
        boolean boolean24 = headLabCoordinator23.getIsDepartmentApproved();
        boolean boolean25 = headLabCoordinator23.getIsDepartmentApproved();
        int int26 = headLabCoordinator23.getHourlyRate();
        boolean boolean27 = department0.approveAccount((model.userhierarchy.User) headLabCoordinator23);
        org.junit.Assert.assertTrue("'" + int7 + "' != '" + 10 + "'", int7 == 10);
        org.junit.Assert.assertTrue("'" + userType10 + "' != '" + model.enums.UserType.STUDENT + "'", userType10.equals(model.enums.UserType.STUDENT));
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + true + "'", boolean11 == true);
        org.junit.Assert.assertNotNull(headLabCoordinator12);
        org.junit.Assert.assertEquals("'" + str13 + "' != '" + "" + "'", str13, "");
        org.junit.Assert.assertTrue("'" + int14 + "' != '" + 0 + "'", int14 == 0);
        org.junit.Assert.assertTrue("'" + int15 + "' != '" + 0 + "'", int15 == 0);
        org.junit.Assert.assertTrue("'" + boolean22 + "' != '" + true + "'", boolean22 == true);
        org.junit.Assert.assertNotNull(headLabCoordinator23);
        org.junit.Assert.assertTrue("'" + boolean24 + "' != '" + true + "'", boolean24 == true);
        org.junit.Assert.assertTrue("'" + boolean25 + "' != '" + true + "'", boolean25 == true);
        org.junit.Assert.assertTrue("'" + int26 + "' != '" + 0 + "'", int26 == 0);
        org.junit.Assert.assertTrue("'" + boolean27 + "' != '" + true + "'", boolean27 == true);
    }

    @Test
    public void test215() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test215");
        model.userhierarchy.LabManager labManager5 = new model.userhierarchy.LabManager((int) '4', "model.exceptions.EmailNotUniqueException: ", "hi!", "hi!", (int) (short) 10);
        java.lang.String str6 = labManager5.getUsername();
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "hi!" + "'", str6, "hi!");
    }

    @Test
    public void test216() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test216");
        model.userhierarchy.HeadLabCoordinator headLabCoordinator0 = model.userhierarchy.HeadLabCoordinator.getInstance();
        boolean boolean1 = headLabCoordinator0.getIsDepartmentApproved();
        boolean boolean2 = headLabCoordinator0.getIsDepartmentApproved();
        headLabCoordinator0.setIsDepartmentApproved(true);
        headLabCoordinator0.initialize(36, "hi!", "1  hi!  ENABLED", "", 9);
        org.junit.Assert.assertNotNull(headLabCoordinator0);
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + true + "'", boolean1 == true);
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + true + "'", boolean2 == true);
    }

    @Test
    public void test217() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test217");
        model.enums.EquipmentStatus equipmentStatus4 = null;
        model.Equipment equipment5 = new model.Equipment((int) '#', "model.exceptions.WeakPasswordException: hi!", "model.exceptions.WeakPasswordException: hi!", "1  hi!  ENABLED", equipmentStatus4);
        java.lang.Class<?> wildcardClass6 = equipment5.getClass();
        org.junit.Assert.assertNotNull(wildcardClass6);
    }

    @Test
    public void test218() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test218");
        model.IdGeneration idGeneration0 = model.IdGeneration.getInstance();
        int int1 = idGeneration0.nextUserId();
        idGeneration0.setEquipmentIdCount((int) (short) 10);
        int int4 = idGeneration0.nextReservationId();
        int int5 = idGeneration0.nextReservationId();
        int int6 = idGeneration0.nextReservationId();
        int int7 = idGeneration0.nextReservationId();
        int int8 = idGeneration0.nextUserId();
        org.junit.Assert.assertNotNull(idGeneration0);
// flaky "18) test218(RegressionTest0)":         org.junit.Assert.assertTrue("'" + int1 + "' != '" + 7 + "'", int1 == 7);
// flaky "13) test218(RegressionTest0)":         org.junit.Assert.assertTrue("'" + int4 + "' != '" + 10 + "'", int4 == 10);
// flaky "9) test218(RegressionTest0)":         org.junit.Assert.assertTrue("'" + int5 + "' != '" + 11 + "'", int5 == 11);
// flaky "4) test218(RegressionTest0)":         org.junit.Assert.assertTrue("'" + int6 + "' != '" + 12 + "'", int6 == 12);
// flaky "1) test218(RegressionTest0)":         org.junit.Assert.assertTrue("'" + int7 + "' != '" + 13 + "'", int7 == 13);
// flaky "1) test218(RegressionTest0)":         org.junit.Assert.assertTrue("'" + int8 + "' != '" + 8 + "'", int8 == 8);
    }

    @Test
    public void test219() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test219");
        model.userhierarchy.HeadLabCoordinator headLabCoordinator0 = model.userhierarchy.HeadLabCoordinator.getInstance();
        boolean boolean1 = headLabCoordinator0.getIsDepartmentApproved();
        boolean boolean2 = headLabCoordinator0.getIsDepartmentApproved();
        headLabCoordinator0.setUserId((int) (byte) 1);
        headLabCoordinator0.initialize(2, "model.exceptions.EmailNotUniqueException: ", "", "", (int) (short) 0);
        headLabCoordinator0.initialize(9, "", "1 hi! model.exceptions.EmailNotUniqueException:   ENABLED", "", 12);
        headLabCoordinator0.initialize((int) (byte) 1, "model.exceptions.WeakPasswordException: model.exceptions.WeakPasswordException: hi!", "model.exceptions.WeakPasswordException: hi!", "hi!", 6);
        org.junit.Assert.assertNotNull(headLabCoordinator0);
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + true + "'", boolean1 == true);
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + true + "'", boolean2 == true);
    }

    @Test
    public void test220() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test220");
        model.IdGeneration idGeneration0 = model.IdGeneration.getInstance();
        int int1 = idGeneration0.nextUserId();
        idGeneration0.setEquipmentIdCount((int) (short) 10);
        idGeneration0.setReservationIdCount(33);
        int int6 = idGeneration0.nextUserId();
        org.junit.Assert.assertNotNull(idGeneration0);
// flaky "19) test220(RegressionTest0)":         org.junit.Assert.assertTrue("'" + int1 + "' != '" + 9 + "'", int1 == 9);
// flaky "14) test220(RegressionTest0)":         org.junit.Assert.assertTrue("'" + int6 + "' != '" + 10 + "'", int6 == 10);
    }

    @Test
    public void test221() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test221");
        java.time.LocalDateTime localDateTime1 = null;
        java.time.LocalDateTime localDateTime2 = null;
        model.Reservation reservation5 = new model.Reservation(0, localDateTime1, localDateTime2, 2, 10);
        reservation5.setId((int) (short) 1);
        java.time.LocalDateTime localDateTime8 = reservation5.getStartTime();
        reservation5.setEquipmentId((int) ' ');
        java.time.LocalDateTime localDateTime11 = null;
        reservation5.setEndTime(localDateTime11);
        reservation5.setTotalOwed((int) (short) 10);
        org.junit.Assert.assertNull(localDateTime8);
    }

    @Test
    public void test222() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test222");
        model.userhierarchy.LabManager labManager5 = new model.userhierarchy.LabManager(5, "model.exceptions.WeakPasswordException: model.exceptions.WeakPasswordException: hi!", "", "model.exceptions.EmailNotUniqueException: ", 35);
    }

    @Test
    public void test223() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test223");
        model.paymentclasses.ResearchGrantPayment researchGrantPayment0 = new model.paymentclasses.ResearchGrantPayment();
        researchGrantPayment0.makePayment(4);
        researchGrantPayment0.makePayment(1);
    }

    @Test
    public void test224() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test224");
        model.Equipment equipment4 = new model.Equipment((int) (byte) 10, "model.exceptions.WeakPasswordException: hi!", "model.exceptions.WeakPasswordException: hi!", "");
        equipment4.setLabLocation("1 hi! model.exceptions.EmailNotUniqueException:   ENABLED");
        int int7 = equipment4.getId();
        equipment4.setName("model.exceptions.WeakPasswordException: hi!");
        org.junit.Assert.assertTrue("'" + int7 + "' != '" + 10 + "'", int7 == 10);
    }

    @Test
    public void test225() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test225");
        java.time.LocalDateTime localDateTime1 = null;
        java.time.LocalDateTime localDateTime2 = null;
        model.Reservation reservation5 = new model.Reservation(0, localDateTime1, localDateTime2, 2, 10);
        reservation5.setId((int) (short) 1);
        java.time.LocalDateTime localDateTime8 = reservation5.getStartTime();
        reservation5.setEquipmentId((int) ' ');
        reservation5.setDepositAmount(20);
        int int13 = reservation5.getId();
        int int14 = reservation5.getDepositAmount();
        org.junit.Assert.assertNull(localDateTime8);
        org.junit.Assert.assertTrue("'" + int13 + "' != '" + 1 + "'", int13 == 1);
        org.junit.Assert.assertTrue("'" + int14 + "' != '" + 20 + "'", int14 == 20);
    }

    @Test
    public void test226() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test226");
        model.userhierarchy.HeadLabCoordinator headLabCoordinator0 = model.userhierarchy.HeadLabCoordinator.getInstance();
        boolean boolean1 = headLabCoordinator0.getIsDepartmentApproved();
        boolean boolean2 = headLabCoordinator0.getIsDepartmentApproved();
        headLabCoordinator0.setUserId((int) (byte) 1);
        int int5 = headLabCoordinator0.getUserId();
        int int6 = headLabCoordinator0.getHourlyRate();
        int int7 = headLabCoordinator0.getUserId();
        org.junit.Assert.assertNotNull(headLabCoordinator0);
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + true + "'", boolean1 == true);
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + true + "'", boolean2 == true);
        org.junit.Assert.assertTrue("'" + int5 + "' != '" + 1 + "'", int5 == 1);
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 0 + "'", int6 == 0);
        org.junit.Assert.assertTrue("'" + int7 + "' != '" + 1 + "'", int7 == 1);
    }

    @Test
    public void test227() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test227");
        model.Equipment equipment8 = new model.Equipment((int) (short) 1, "hi!", "hi!", "");
        equipment8.setName("");
        model.enums.EquipmentStatus equipmentStatus11 = model.enums.EquipmentStatus.ENABLED;
        equipment8.setStatus(equipmentStatus11);
        model.Equipment equipment17 = new model.Equipment((int) (short) 1, "hi!", "hi!", "");
        model.enums.EquipmentStatus equipmentStatus18 = model.enums.EquipmentStatus.ENABLED;
        equipment17.setStatus(equipmentStatus18);
        equipment8.setStatus(equipmentStatus18);
        model.Equipment equipment21 = new model.Equipment(97, "model.exceptions.WeakPasswordException: hi!", "hi!", "1 model.exceptions.WeakPasswordException: hi! hi!  ENABLED", equipmentStatus18);
        java.lang.String str22 = equipment21.getDescription();
        org.junit.Assert.assertTrue("'" + equipmentStatus11 + "' != '" + model.enums.EquipmentStatus.ENABLED + "'", equipmentStatus11.equals(model.enums.EquipmentStatus.ENABLED));
        org.junit.Assert.assertTrue("'" + equipmentStatus18 + "' != '" + model.enums.EquipmentStatus.ENABLED + "'", equipmentStatus18.equals(model.enums.EquipmentStatus.ENABLED));
        org.junit.Assert.assertEquals("'" + str22 + "' != '" + "hi!" + "'", str22, "hi!");
    }

    @Test
    public void test228() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test228");
        java.time.LocalDateTime localDateTime1 = null;
        java.time.LocalDateTime localDateTime2 = null;
        model.Reservation reservation5 = new model.Reservation(0, localDateTime1, localDateTime2, 2, 10);
        int int6 = reservation5.getTotalOwed();
        java.time.LocalDateTime localDateTime7 = reservation5.getEndTime();
        java.time.LocalDateTime localDateTime8 = reservation5.getStartTime();
        model.Equipment equipment13 = new model.Equipment((int) (short) 1, "hi!", "hi!", "");
        model.Sensor sensor14 = new model.Sensor(equipment13);
        model.enums.EquipmentStatus equipmentStatus15 = equipment13.getStatus();
        model.Sensor sensor16 = new model.Sensor(equipment13);
        reservation5.update(sensor16, "model.exceptions.WeakPasswordException: hi!");
        java.time.LocalDateTime localDateTime20 = null;
        java.time.LocalDateTime localDateTime21 = null;
        model.Reservation reservation24 = new model.Reservation(0, localDateTime20, localDateTime21, 2, 10);
        reservation24.setId((int) (short) 1);
        java.time.LocalDateTime localDateTime27 = reservation24.getStartTime();
        int int28 = reservation24.getEquipmentId();
        int int29 = reservation24.getUserId();
        int int30 = reservation24.getEquipmentId();
        sensor16.detach((model.SensorObserver) reservation24);
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 0 + "'", int6 == 0);
        org.junit.Assert.assertNull(localDateTime7);
        org.junit.Assert.assertNull(localDateTime8);
        org.junit.Assert.assertTrue("'" + equipmentStatus15 + "' != '" + model.enums.EquipmentStatus.ENABLED + "'", equipmentStatus15.equals(model.enums.EquipmentStatus.ENABLED));
        org.junit.Assert.assertNull(localDateTime27);
        org.junit.Assert.assertTrue("'" + int28 + "' != '" + 10 + "'", int28 == 10);
        org.junit.Assert.assertTrue("'" + int29 + "' != '" + 2 + "'", int29 == 2);
        org.junit.Assert.assertTrue("'" + int30 + "' != '" + 10 + "'", int30 == 10);
    }

    @Test
    public void test229() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test229");
        model.Department department0 = new model.Department();
        model.userhierarchy.Guest guest6 = new model.userhierarchy.Guest(3, "hi!", "hi!", "", 100);
        java.lang.String str7 = guest6.getUsername();
        boolean boolean8 = department0.approveAccount((model.userhierarchy.User) guest6);
        model.userhierarchy.HeadLabCoordinator headLabCoordinator9 = model.userhierarchy.HeadLabCoordinator.getInstance();
        java.lang.String str10 = headLabCoordinator9.getPassword();
        int int11 = headLabCoordinator9.getHourlyRate();
        model.enums.UserType userType12 = headLabCoordinator9.getUserType();
        java.lang.String str13 = headLabCoordinator9.getUsername();
        boolean boolean14 = department0.approveAccount((model.userhierarchy.User) headLabCoordinator9);
        model.userhierarchy.Student student20 = new model.userhierarchy.Student((int) (short) 0, "hi!", "hi!", "hi!", 0);
        int int21 = student20.getId();
        boolean boolean22 = department0.approveAccount((model.userhierarchy.User) student20);
        student20.setUniversityAfilliated(false);
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + "hi!" + "'", str7, "hi!");
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertNotNull(headLabCoordinator9);
        org.junit.Assert.assertEquals("'" + str10 + "' != '" + "hi!" + "'", str10, "hi!");
        org.junit.Assert.assertTrue("'" + int11 + "' != '" + 0 + "'", int11 == 0);
        org.junit.Assert.assertTrue("'" + userType12 + "' != '" + model.enums.UserType.HEADLABCOORDINATOR + "'", userType12.equals(model.enums.UserType.HEADLABCOORDINATOR));
        org.junit.Assert.assertEquals("'" + str13 + "' != '" + "model.exceptions.WeakPasswordException: hi!" + "'", str13, "model.exceptions.WeakPasswordException: hi!");
        org.junit.Assert.assertTrue("'" + boolean14 + "' != '" + true + "'", boolean14 == true);
        org.junit.Assert.assertTrue("'" + int21 + "' != '" + 0 + "'", int21 == 0);
        org.junit.Assert.assertTrue("'" + boolean22 + "' != '" + true + "'", boolean22 == true);
    }

    @Test
    public void test230() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test230");
        model.userhierarchy.Student student5 = new model.userhierarchy.Student((int) (short) 0, "hi!", "hi!", "hi!", 0);
        int int6 = student5.getHourlyRate();
        java.lang.String str7 = student5.getUsername();
        int int8 = student5.getId();
        int int9 = student5.getId();
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 10 + "'", int6 == 10);
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + "hi!" + "'", str7, "hi!");
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 0 + "'", int8 == 0);
        org.junit.Assert.assertTrue("'" + int9 + "' != '" + 0 + "'", int9 == 0);
    }

    @Test
    public void test231() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test231");
        model.userhierarchy.Student student5 = new model.userhierarchy.Student(20, "1 model.exceptions.WeakPasswordException: hi! hi!  ENABLED", "1  hi!  ENABLED", "model.exceptions.WeakPasswordException: model.exceptions.WeakPasswordException: hi!", 37);
    }

    @Test
    public void test232() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test232");
        java.time.LocalDateTime localDateTime1 = null;
        java.time.LocalDateTime localDateTime2 = null;
        model.Reservation reservation5 = new model.Reservation(0, localDateTime1, localDateTime2, 2, 10);
        int int6 = reservation5.getTotalOwed();
        java.time.LocalDateTime localDateTime7 = reservation5.getEndTime();
        int int8 = reservation5.getEquipmentId();
        int int9 = reservation5.getTotalOwed();
        reservation5.setEquipmentId(32);
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 0 + "'", int6 == 0);
        org.junit.Assert.assertNull(localDateTime7);
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 10 + "'", int8 == 10);
        org.junit.Assert.assertTrue("'" + int9 + "' != '" + 0 + "'", int9 == 0);
    }

    @Test
    public void test233() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test233");
        java.time.LocalDateTime localDateTime1 = null;
        java.time.LocalDateTime localDateTime2 = null;
        model.Reservation reservation5 = new model.Reservation(0, localDateTime1, localDateTime2, 2, 10);
        reservation5.setId((int) (short) 1);
        java.time.LocalDateTime localDateTime8 = reservation5.getStartTime();
        int int9 = reservation5.getEquipmentId();
        int int10 = reservation5.getUserId();
        model.Equipment equipment15 = new model.Equipment((int) (short) 1, "hi!", "hi!", "");
        model.Sensor sensor16 = new model.Sensor(equipment15);
        model.enums.EquipmentStatus equipmentStatus17 = equipment15.getStatus();
        model.Sensor sensor18 = new model.Sensor(equipment15);
        reservation5.update(sensor18, "");
        model.Equipment equipment25 = new model.Equipment((int) (short) 1, "hi!", "hi!", "");
        model.Sensor sensor26 = new model.Sensor(equipment25);
        model.enums.EquipmentStatus equipmentStatus27 = equipment25.getStatus();
        model.Sensor sensor28 = new model.Sensor(equipment25);
        model.enums.EquipmentStatus equipmentStatus29 = model.enums.EquipmentStatus.ENABLED;
        equipment25.setStatus(equipmentStatus29);
        model.Equipment equipment35 = new model.Equipment((int) (short) 1, "hi!", "hi!", "");
        equipment35.setName("");
        model.enums.EquipmentStatus equipmentStatus38 = model.enums.EquipmentStatus.ENABLED;
        equipment35.setStatus(equipmentStatus38);
        model.Sensor sensor40 = new model.Sensor(equipment35);
        equipment25.update(sensor40, "hi!");
        sensor18.attach((model.SensorObserver) equipment25);
        model.enums.EquipmentStatus equipmentStatus44 = equipment25.getStatus();
        model.Equipment equipment49 = new model.Equipment((int) (short) 1, "hi!", "hi!", "");
        equipment49.setName("");
        model.enums.EquipmentStatus equipmentStatus52 = model.enums.EquipmentStatus.ENABLED;
        equipment49.setStatus(equipmentStatus52);
        model.Sensor sensor54 = new model.Sensor(equipment49);
        model.enums.EquipmentStatus equipmentStatus55 = model.enums.EquipmentStatus.ENABLED;
        equipment49.setStatus(equipmentStatus55);
        equipment25.setStatus(equipmentStatus55);
        model.Sensor sensor58 = new model.Sensor(equipment25);
        sensor58.departure();
        org.junit.Assert.assertNull(localDateTime8);
        org.junit.Assert.assertTrue("'" + int9 + "' != '" + 10 + "'", int9 == 10);
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 2 + "'", int10 == 2);
        org.junit.Assert.assertTrue("'" + equipmentStatus17 + "' != '" + model.enums.EquipmentStatus.ENABLED + "'", equipmentStatus17.equals(model.enums.EquipmentStatus.ENABLED));
        org.junit.Assert.assertTrue("'" + equipmentStatus27 + "' != '" + model.enums.EquipmentStatus.ENABLED + "'", equipmentStatus27.equals(model.enums.EquipmentStatus.ENABLED));
        org.junit.Assert.assertTrue("'" + equipmentStatus29 + "' != '" + model.enums.EquipmentStatus.ENABLED + "'", equipmentStatus29.equals(model.enums.EquipmentStatus.ENABLED));
        org.junit.Assert.assertTrue("'" + equipmentStatus38 + "' != '" + model.enums.EquipmentStatus.ENABLED + "'", equipmentStatus38.equals(model.enums.EquipmentStatus.ENABLED));
        org.junit.Assert.assertTrue("'" + equipmentStatus44 + "' != '" + model.enums.EquipmentStatus.ENABLED + "'", equipmentStatus44.equals(model.enums.EquipmentStatus.ENABLED));
        org.junit.Assert.assertTrue("'" + equipmentStatus52 + "' != '" + model.enums.EquipmentStatus.ENABLED + "'", equipmentStatus52.equals(model.enums.EquipmentStatus.ENABLED));
        org.junit.Assert.assertTrue("'" + equipmentStatus55 + "' != '" + model.enums.EquipmentStatus.ENABLED + "'", equipmentStatus55.equals(model.enums.EquipmentStatus.ENABLED));
    }

    @Test
    public void test234() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test234");
        model.userhierarchy.Researcher researcher5 = new model.userhierarchy.Researcher((int) (short) 0, "hi!", "model.exceptions.EmailNotUniqueException: ", "", 10);
        int int6 = researcher5.getVerificationNum();
        int int7 = researcher5.getHourlyRate();
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 10 + "'", int6 == 10);
        org.junit.Assert.assertTrue("'" + int7 + "' != '" + 20 + "'", int7 == 20);
    }

    @Test
    public void test235() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test235");
        model.Equipment equipment4 = new model.Equipment((int) (short) 1, "hi!", "hi!", "");
        java.lang.String str5 = equipment4.getLabLocation();
        equipment4.setLabLocation("model.exceptions.WeakPasswordException: hi!");
        model.Sensor sensor8 = equipment4.getSensor();
        model.Equipment equipment13 = new model.Equipment((int) (short) 1, "hi!", "hi!", "");
        java.lang.String str14 = equipment13.getLabLocation();
        model.Sensor sensor15 = equipment13.getSensor();
        sensor15.notifyObservers("");
        model.Equipment equipment22 = new model.Equipment((int) (short) 1, "hi!", "hi!", "");
        java.lang.String str23 = equipment22.getLabLocation();
        model.Sensor sensor24 = equipment22.getSensor();
        sensor15.detach((model.SensorObserver) equipment22);
        int int26 = equipment22.getId();
        sensor8.attach((model.SensorObserver) equipment22);
        org.junit.Assert.assertEquals("'" + str5 + "' != '" + "" + "'", str5, "");
        org.junit.Assert.assertNotNull(sensor8);
        org.junit.Assert.assertEquals("'" + str14 + "' != '" + "" + "'", str14, "");
        org.junit.Assert.assertNotNull(sensor15);
        org.junit.Assert.assertEquals("'" + str23 + "' != '" + "" + "'", str23, "");
        org.junit.Assert.assertNotNull(sensor24);
        org.junit.Assert.assertTrue("'" + int26 + "' != '" + 1 + "'", int26 == 1);
    }

    @Test
    public void test236() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test236");
        java.time.LocalDateTime localDateTime1 = null;
        java.time.LocalDateTime localDateTime2 = null;
        model.Reservation reservation5 = new model.Reservation(0, localDateTime1, localDateTime2, 2, 10);
        int int6 = reservation5.getTotalOwed();
        int int7 = reservation5.getEquipmentId();
        int int8 = reservation5.getId();
        reservation5.setDepositAmount(7);
        int int11 = reservation5.getDepositAmount();
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 0 + "'", int6 == 0);
        org.junit.Assert.assertTrue("'" + int7 + "' != '" + 10 + "'", int7 == 10);
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 0 + "'", int8 == 0);
        org.junit.Assert.assertTrue("'" + int11 + "' != '" + 7 + "'", int11 == 7);
    }

    @Test
    public void test237() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test237");
        model.UserFactory userFactory0 = new model.UserFactory();
        model.enums.UserType userType6 = model.enums.UserType.LABMANAGER;
        model.userhierarchy.User user7 = userFactory0.createUser(5, "1  hi!  ENABLED", "", "model.exceptions.WeakPasswordException: hi!", (-1), userType6);
        org.junit.Assert.assertTrue("'" + userType6 + "' != '" + model.enums.UserType.LABMANAGER + "'", userType6.equals(model.enums.UserType.LABMANAGER));
        org.junit.Assert.assertNotNull(user7);
    }

    @Test
    public void test238() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test238");
        model.Checkout checkout0 = new model.Checkout();
        model.paymentclasses.ResearchGrantPayment researchGrantPayment1 = new model.paymentclasses.ResearchGrantPayment();
        researchGrantPayment1.makePayment(4);
        checkout0.setPaymentStrategy((model.paymentclasses.PaymentStrategy) researchGrantPayment1);
        researchGrantPayment1.makePayment(8);
        researchGrantPayment1.makePayment(14);
    }

    @Test
    public void test239() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test239");
        model.userhierarchy.HeadLabCoordinator headLabCoordinator0 = model.userhierarchy.HeadLabCoordinator.getInstance();
        java.lang.String str1 = headLabCoordinator0.getPassword();
        int int2 = headLabCoordinator0.getHourlyRate();
        model.enums.UserType userType3 = headLabCoordinator0.getUserType();
        int int4 = headLabCoordinator0.getHourlyRate();
        org.junit.Assert.assertNotNull(headLabCoordinator0);
        org.junit.Assert.assertEquals("'" + str1 + "' != '" + "hi!" + "'", str1, "hi!");
        org.junit.Assert.assertTrue("'" + int2 + "' != '" + 0 + "'", int2 == 0);
        org.junit.Assert.assertTrue("'" + userType3 + "' != '" + model.enums.UserType.HEADLABCOORDINATOR + "'", userType3.equals(model.enums.UserType.HEADLABCOORDINATOR));
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 0 + "'", int4 == 0);
    }

    @Test
    public void test240() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test240");
        model.Equipment equipment4 = new model.Equipment((int) (short) 1, "hi!", "hi!", "");
        model.Sensor sensor5 = new model.Sensor(equipment4);
        model.enums.EquipmentStatus equipmentStatus6 = equipment4.getStatus();
        model.Sensor sensor7 = equipment4.getSensor();
        model.Equipment equipment12 = new model.Equipment((int) (short) 1, "hi!", "hi!", "");
        java.lang.String str13 = equipment12.getLabLocation();
        model.Sensor sensor14 = equipment12.getSensor();
        sensor14.notifyObservers("");
        model.Equipment equipment21 = new model.Equipment((int) (short) 1, "hi!", "hi!", "");
        java.lang.String str22 = equipment21.getLabLocation();
        model.Sensor sensor23 = equipment21.getSensor();
        sensor14.detach((model.SensorObserver) equipment21);
        java.time.LocalDateTime localDateTime26 = null;
        java.time.LocalDateTime localDateTime27 = null;
        model.Reservation reservation30 = new model.Reservation(0, localDateTime26, localDateTime27, 2, 10);
        reservation30.setId((int) (short) 1);
        java.time.LocalDateTime localDateTime33 = reservation30.getStartTime();
        int int34 = reservation30.getEquipmentId();
        int int35 = reservation30.getUserId();
        model.Equipment equipment40 = new model.Equipment((int) (short) 1, "hi!", "hi!", "");
        model.Sensor sensor41 = new model.Sensor(equipment40);
        model.enums.EquipmentStatus equipmentStatus42 = equipment40.getStatus();
        model.Sensor sensor43 = new model.Sensor(equipment40);
        reservation30.update(sensor43, "");
        model.Equipment equipment50 = new model.Equipment((int) (short) 1, "hi!", "hi!", "");
        model.Sensor sensor51 = new model.Sensor(equipment50);
        model.enums.EquipmentStatus equipmentStatus52 = equipment50.getStatus();
        model.Sensor sensor53 = new model.Sensor(equipment50);
        model.enums.EquipmentStatus equipmentStatus54 = model.enums.EquipmentStatus.ENABLED;
        equipment50.setStatus(equipmentStatus54);
        model.Equipment equipment60 = new model.Equipment((int) (short) 1, "hi!", "hi!", "");
        equipment60.setName("");
        model.enums.EquipmentStatus equipmentStatus63 = model.enums.EquipmentStatus.ENABLED;
        equipment60.setStatus(equipmentStatus63);
        model.Sensor sensor65 = new model.Sensor(equipment60);
        equipment50.update(sensor65, "hi!");
        sensor43.attach((model.SensorObserver) equipment50);
        sensor14.attach((model.SensorObserver) equipment50);
        equipment4.update(sensor14, "1 hi! model.exceptions.EmailNotUniqueException:   ENABLED");
        java.lang.String str72 = equipment4.getName();
        java.lang.String str73 = equipment4.getName();
        org.junit.Assert.assertTrue("'" + equipmentStatus6 + "' != '" + model.enums.EquipmentStatus.ENABLED + "'", equipmentStatus6.equals(model.enums.EquipmentStatus.ENABLED));
        org.junit.Assert.assertNotNull(sensor7);
        org.junit.Assert.assertEquals("'" + str13 + "' != '" + "" + "'", str13, "");
        org.junit.Assert.assertNotNull(sensor14);
        org.junit.Assert.assertEquals("'" + str22 + "' != '" + "" + "'", str22, "");
        org.junit.Assert.assertNotNull(sensor23);
        org.junit.Assert.assertNull(localDateTime33);
        org.junit.Assert.assertTrue("'" + int34 + "' != '" + 10 + "'", int34 == 10);
        org.junit.Assert.assertTrue("'" + int35 + "' != '" + 2 + "'", int35 == 2);
        org.junit.Assert.assertTrue("'" + equipmentStatus42 + "' != '" + model.enums.EquipmentStatus.ENABLED + "'", equipmentStatus42.equals(model.enums.EquipmentStatus.ENABLED));
        org.junit.Assert.assertTrue("'" + equipmentStatus52 + "' != '" + model.enums.EquipmentStatus.ENABLED + "'", equipmentStatus52.equals(model.enums.EquipmentStatus.ENABLED));
        org.junit.Assert.assertTrue("'" + equipmentStatus54 + "' != '" + model.enums.EquipmentStatus.ENABLED + "'", equipmentStatus54.equals(model.enums.EquipmentStatus.ENABLED));
        org.junit.Assert.assertTrue("'" + equipmentStatus63 + "' != '" + model.enums.EquipmentStatus.ENABLED + "'", equipmentStatus63.equals(model.enums.EquipmentStatus.ENABLED));
        org.junit.Assert.assertEquals("'" + str72 + "' != '" + "hi!" + "'", str72, "hi!");
        org.junit.Assert.assertEquals("'" + str73 + "' != '" + "hi!" + "'", str73, "hi!");
    }

    @Test
    public void test241() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test241");
        java.time.LocalDateTime localDateTime1 = null;
        java.time.LocalDateTime localDateTime2 = null;
        model.Reservation reservation5 = new model.Reservation(0, localDateTime1, localDateTime2, 2, 10);
        reservation5.setId((int) (short) 1);
        java.time.LocalDateTime localDateTime8 = reservation5.getStartTime();
        reservation5.setEquipmentId((int) ' ');
        reservation5.setDepositAmount(20);
        int int13 = reservation5.getEquipmentId();
        reservation5.setId(38);
        org.junit.Assert.assertNull(localDateTime8);
        org.junit.Assert.assertTrue("'" + int13 + "' != '" + 32 + "'", int13 == 32);
    }

    @Test
    public void test242() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test242");
        model.Department department0 = new model.Department();
        model.userhierarchy.Guest guest6 = new model.userhierarchy.Guest(3, "hi!", "hi!", "", 100);
        java.lang.String str7 = guest6.getUsername();
        boolean boolean8 = department0.approveAccount((model.userhierarchy.User) guest6);
        model.userhierarchy.HeadLabCoordinator headLabCoordinator9 = model.userhierarchy.HeadLabCoordinator.getInstance();
        java.lang.String str10 = headLabCoordinator9.getPassword();
        int int11 = headLabCoordinator9.getHourlyRate();
        model.enums.UserType userType12 = headLabCoordinator9.getUserType();
        java.lang.String str13 = headLabCoordinator9.getUsername();
        boolean boolean14 = department0.approveAccount((model.userhierarchy.User) headLabCoordinator9);
        model.enums.UserType userType15 = headLabCoordinator9.getUserType();
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + "hi!" + "'", str7, "hi!");
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertNotNull(headLabCoordinator9);
        org.junit.Assert.assertEquals("'" + str10 + "' != '" + "hi!" + "'", str10, "hi!");
        org.junit.Assert.assertTrue("'" + int11 + "' != '" + 0 + "'", int11 == 0);
        org.junit.Assert.assertTrue("'" + userType12 + "' != '" + model.enums.UserType.HEADLABCOORDINATOR + "'", userType12.equals(model.enums.UserType.HEADLABCOORDINATOR));
        org.junit.Assert.assertEquals("'" + str13 + "' != '" + "model.exceptions.WeakPasswordException: hi!" + "'", str13, "model.exceptions.WeakPasswordException: hi!");
        org.junit.Assert.assertTrue("'" + boolean14 + "' != '" + true + "'", boolean14 == true);
        org.junit.Assert.assertTrue("'" + userType15 + "' != '" + model.enums.UserType.HEADLABCOORDINATOR + "'", userType15.equals(model.enums.UserType.HEADLABCOORDINATOR));
    }

    @Test
    public void test243() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test243");
        model.enums.EquipmentStatus equipmentStatus4 = model.enums.EquipmentStatus.MAINTENANCE;
        model.Equipment equipment5 = new model.Equipment(10, "model.exceptions.WeakPasswordException: hi!", "model.exceptions.WeakPasswordException: model.exceptions.WeakPasswordException: hi!", "1  hi!  ENABLED", equipmentStatus4);
        java.lang.String str6 = equipment5.toString();
        org.junit.Assert.assertTrue("'" + equipmentStatus4 + "' != '" + model.enums.EquipmentStatus.MAINTENANCE + "'", equipmentStatus4.equals(model.enums.EquipmentStatus.MAINTENANCE));
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "10 model.exceptions.WeakPasswordException: hi! model.exceptions.WeakPasswordException: model.exceptions.WeakPasswordException: hi! 1  hi!  ENABLED MAINTENANCE" + "'", str6, "10 model.exceptions.WeakPasswordException: hi! model.exceptions.WeakPasswordException: model.exceptions.WeakPasswordException: hi! 1  hi!  ENABLED MAINTENANCE");
    }

    @Test
    public void test244() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test244");
        model.Equipment equipment4 = new model.Equipment((int) (short) 1, "hi!", "hi!", "");
        java.lang.String str5 = equipment4.getName();
        equipment4.setName("model.exceptions.WeakPasswordException: hi!");
        java.lang.String str8 = equipment4.toString();
        equipment4.setName("10 model.exceptions.WeakPasswordException: hi! model.exceptions.WeakPasswordException: model.exceptions.WeakPasswordException: hi! 1  hi!  ENABLED MAINTENANCE");
        org.junit.Assert.assertEquals("'" + str5 + "' != '" + "hi!" + "'", str5, "hi!");
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "1 model.exceptions.WeakPasswordException: hi! hi!  ENABLED" + "'", str8, "1 model.exceptions.WeakPasswordException: hi! hi!  ENABLED");
    }

    @Test
    public void test245() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test245");
        model.Checkout checkout0 = new model.Checkout();
        model.paymentclasses.ResearchGrantPayment researchGrantPayment1 = new model.paymentclasses.ResearchGrantPayment();
        researchGrantPayment1.makePayment(4);
        checkout0.setPaymentStrategy((model.paymentclasses.PaymentStrategy) researchGrantPayment1);
        checkout0.makePayment(9);
        checkout0.makePayment(22);
    }

    @Test
    public void test246() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test246");
        model.userhierarchy.Researcher researcher5 = new model.userhierarchy.Researcher(13, "1  hi!  ENABLED", "hi!", "1 hi! hi! model.exceptions.WeakPasswordException: hi! ENABLED", (int) (short) -1);
    }

    @Test
    public void test247() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test247");
        model.IdGeneration idGeneration0 = model.IdGeneration.getInstance();
        int int1 = idGeneration0.nextUserId();
        idGeneration0.setEquipmentIdCount((int) (short) 10);
        int int4 = idGeneration0.nextReservationId();
        int int5 = idGeneration0.nextReservationId();
        int int6 = idGeneration0.nextReservationId();
        int int7 = idGeneration0.nextReservationId();
        int int8 = idGeneration0.nextEquipmentId();
        org.junit.Assert.assertNotNull(idGeneration0);
// flaky "20) test247(RegressionTest0)":         org.junit.Assert.assertTrue("'" + int1 + "' != '" + 11 + "'", int1 == 11);
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 33 + "'", int4 == 33);
        org.junit.Assert.assertTrue("'" + int5 + "' != '" + 34 + "'", int5 == 34);
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 35 + "'", int6 == 35);
        org.junit.Assert.assertTrue("'" + int7 + "' != '" + 36 + "'", int7 == 36);
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 10 + "'", int8 == 10);
    }

    @Test
    public void test248() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test248");
        java.time.LocalDateTime localDateTime1 = null;
        java.time.LocalDateTime localDateTime2 = null;
        model.Reservation reservation5 = new model.Reservation(0, localDateTime1, localDateTime2, 2, 10);
        reservation5.setId((int) (short) 1);
        java.time.LocalDateTime localDateTime8 = reservation5.getStartTime();
        int int9 = reservation5.getEquipmentId();
        int int10 = reservation5.getUserId();
        model.Equipment equipment15 = new model.Equipment((int) (short) 1, "hi!", "hi!", "");
        model.Sensor sensor16 = new model.Sensor(equipment15);
        model.enums.EquipmentStatus equipmentStatus17 = equipment15.getStatus();
        model.Sensor sensor18 = new model.Sensor(equipment15);
        reservation5.update(sensor18, "");
        model.Equipment equipment25 = new model.Equipment((int) (short) 1, "hi!", "hi!", "");
        model.Sensor sensor26 = new model.Sensor(equipment25);
        model.enums.EquipmentStatus equipmentStatus27 = equipment25.getStatus();
        model.Sensor sensor28 = new model.Sensor(equipment25);
        model.enums.EquipmentStatus equipmentStatus29 = model.enums.EquipmentStatus.ENABLED;
        equipment25.setStatus(equipmentStatus29);
        model.Equipment equipment35 = new model.Equipment((int) (short) 1, "hi!", "hi!", "");
        equipment35.setName("");
        model.enums.EquipmentStatus equipmentStatus38 = model.enums.EquipmentStatus.ENABLED;
        equipment35.setStatus(equipmentStatus38);
        model.Sensor sensor40 = new model.Sensor(equipment35);
        equipment25.update(sensor40, "hi!");
        sensor18.attach((model.SensorObserver) equipment25);
        sensor18.notifyObservers("1  hi!  ENABLED");
        org.junit.Assert.assertNull(localDateTime8);
        org.junit.Assert.assertTrue("'" + int9 + "' != '" + 10 + "'", int9 == 10);
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 2 + "'", int10 == 2);
        org.junit.Assert.assertTrue("'" + equipmentStatus17 + "' != '" + model.enums.EquipmentStatus.ENABLED + "'", equipmentStatus17.equals(model.enums.EquipmentStatus.ENABLED));
        org.junit.Assert.assertTrue("'" + equipmentStatus27 + "' != '" + model.enums.EquipmentStatus.ENABLED + "'", equipmentStatus27.equals(model.enums.EquipmentStatus.ENABLED));
        org.junit.Assert.assertTrue("'" + equipmentStatus29 + "' != '" + model.enums.EquipmentStatus.ENABLED + "'", equipmentStatus29.equals(model.enums.EquipmentStatus.ENABLED));
        org.junit.Assert.assertTrue("'" + equipmentStatus38 + "' != '" + model.enums.EquipmentStatus.ENABLED + "'", equipmentStatus38.equals(model.enums.EquipmentStatus.ENABLED));
    }

    @Test
    public void test249() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test249");
        model.userhierarchy.Student student5 = new model.userhierarchy.Student((int) (short) 0, "hi!", "hi!", "hi!", 0);
        int int6 = student5.getHourlyRate();
        model.enums.UserType userType7 = student5.getUserType();
        java.lang.String str8 = student5.getPassword();
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 10 + "'", int6 == 10);
        org.junit.Assert.assertTrue("'" + userType7 + "' != '" + model.enums.UserType.STUDENT + "'", userType7.equals(model.enums.UserType.STUDENT));
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "hi!" + "'", str8, "hi!");
    }

    @Test
    public void test250() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test250");
        java.time.LocalDateTime localDateTime1 = null;
        java.time.LocalDateTime localDateTime2 = null;
        model.Reservation reservation5 = new model.Reservation(21, localDateTime1, localDateTime2, 9, (int) '#');
    }

    @Test
    public void test251() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test251");
        java.time.LocalDateTime localDateTime1 = null;
        java.time.LocalDateTime localDateTime2 = null;
        model.Reservation reservation5 = new model.Reservation(0, localDateTime1, localDateTime2, 2, 10);
        int int6 = reservation5.getTotalOwed();
        int int7 = reservation5.getEquipmentId();
        int int8 = reservation5.getUserId();
        java.time.LocalDateTime localDateTime9 = null;
        reservation5.setStartTime(localDateTime9);
        int int11 = reservation5.getTotalOwed();
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 0 + "'", int6 == 0);
        org.junit.Assert.assertTrue("'" + int7 + "' != '" + 10 + "'", int7 == 10);
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 2 + "'", int8 == 2);
        org.junit.Assert.assertTrue("'" + int11 + "' != '" + 0 + "'", int11 == 0);
    }

    @Test
    public void test252() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test252");
        java.time.LocalDateTime localDateTime1 = null;
        java.time.LocalDateTime localDateTime2 = null;
        model.Reservation reservation5 = new model.Reservation((int) ' ', localDateTime1, localDateTime2, 37, (int) (short) -1);
    }

    @Test
    public void test253() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test253");
        java.time.LocalDateTime localDateTime1 = null;
        java.time.LocalDateTime localDateTime2 = null;
        model.Reservation reservation5 = new model.Reservation(0, localDateTime1, localDateTime2, 2, 10);
        reservation5.setId((int) (short) 1);
        java.time.LocalDateTime localDateTime8 = reservation5.getStartTime();
        int int9 = reservation5.getEquipmentId();
        int int10 = reservation5.getUserId();
        int int11 = reservation5.getTotalOwed();
        java.time.LocalDateTime localDateTime12 = reservation5.getEndTime();
        model.Equipment equipment17 = new model.Equipment((int) (short) 1, "hi!", "hi!", "");
        java.lang.String str18 = equipment17.getLabLocation();
        java.lang.String str19 = equipment17.getLabLocation();
        java.lang.String str20 = equipment17.getName();
        model.Sensor sensor21 = equipment17.getSensor();
        reservation5.update(sensor21, "model.exceptions.WeakPasswordException: hi!");
        java.time.LocalDateTime localDateTime24 = reservation5.getStartTime();
        java.time.LocalDateTime localDateTime25 = reservation5.getStartTime();
        reservation5.setTotalOwed((int) '4');
        org.junit.Assert.assertNull(localDateTime8);
        org.junit.Assert.assertTrue("'" + int9 + "' != '" + 10 + "'", int9 == 10);
        org.junit.Assert.assertTrue("'" + int10 + "' != '" + 2 + "'", int10 == 2);
        org.junit.Assert.assertTrue("'" + int11 + "' != '" + 0 + "'", int11 == 0);
        org.junit.Assert.assertNull(localDateTime12);
        org.junit.Assert.assertEquals("'" + str18 + "' != '" + "" + "'", str18, "");
        org.junit.Assert.assertEquals("'" + str19 + "' != '" + "" + "'", str19, "");
        org.junit.Assert.assertEquals("'" + str20 + "' != '" + "hi!" + "'", str20, "hi!");
        org.junit.Assert.assertNotNull(sensor21);
        org.junit.Assert.assertNull(localDateTime24);
        org.junit.Assert.assertNull(localDateTime25);
    }

    @Test
    public void test254() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test254");
        java.time.LocalDateTime localDateTime1 = null;
        java.time.LocalDateTime localDateTime2 = null;
        model.Reservation reservation5 = new model.Reservation(0, localDateTime1, localDateTime2, 2, 10);
        reservation5.setId((int) (short) 1);
        model.enums.ReservationStatus reservationStatus8 = reservation5.getStatus();
        model.enums.ReservationStatus reservationStatus9 = reservation5.getStatus();
        org.junit.Assert.assertNull(reservationStatus8);
        org.junit.Assert.assertNull(reservationStatus9);
    }

    @Test
    public void test255() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test255");
        java.time.LocalDateTime localDateTime1 = null;
        java.time.LocalDateTime localDateTime2 = null;
        model.Reservation reservation5 = new model.Reservation(0, localDateTime1, localDateTime2, 2, 10);
        reservation5.setId((int) (short) 1);
        java.time.LocalDateTime localDateTime8 = reservation5.getStartTime();
        reservation5.setEquipmentId((int) ' ');
        java.time.LocalDateTime localDateTime11 = null;
        reservation5.setStartTime(localDateTime11);
        int int13 = reservation5.getEquipmentId();
        org.junit.Assert.assertNull(localDateTime8);
        org.junit.Assert.assertTrue("'" + int13 + "' != '" + 32 + "'", int13 == 32);
    }

    @Test
    public void test256() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test256");
        java.time.LocalDateTime localDateTime1 = null;
        java.time.LocalDateTime localDateTime2 = null;
        model.Reservation reservation5 = new model.Reservation(0, localDateTime1, localDateTime2, 2, 10);
        reservation5.setId((int) (short) 1);
        java.time.LocalDateTime localDateTime8 = reservation5.getStartTime();
        reservation5.setEquipmentId((int) ' ');
        java.time.LocalDateTime localDateTime11 = null;
        reservation5.setStartTime(localDateTime11);
        model.Equipment equipment17 = new model.Equipment((int) (short) 1, "hi!", "hi!", "");
        equipment17.setName("");
        equipment17.setDescription("hi!");
        equipment17.setDescription("");
        model.Sensor sensor24 = new model.Sensor(equipment17);
        sensor24.departure();
        reservation5.update(sensor24, "1 hi! hi! hi! ENABLED");
        java.time.LocalDateTime localDateTime28 = reservation5.getEndTime();
        org.junit.Assert.assertNull(localDateTime8);
        org.junit.Assert.assertNull(localDateTime28);
    }

    @Test
    public void test257() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test257");
        model.Equipment equipment4 = new model.Equipment((int) (short) 1, "hi!", "hi!", "");
        equipment4.setName("");
        model.enums.EquipmentStatus equipmentStatus7 = model.enums.EquipmentStatus.ENABLED;
        equipment4.setStatus(equipmentStatus7);
        model.Sensor sensor9 = new model.Sensor(equipment4);
        model.Equipment equipment14 = new model.Equipment(15, "model.exceptions.WeakPasswordException: hi!", "hi!", "model.exceptions.WeakPasswordException: model.exceptions.WeakPasswordException: hi!");
        sensor9.detach((model.SensorObserver) equipment14);
        java.time.LocalDateTime localDateTime17 = null;
        java.time.LocalDateTime localDateTime18 = null;
        model.Reservation reservation21 = new model.Reservation(0, localDateTime17, localDateTime18, 2, 10);
        reservation21.setId((int) (short) 1);
        java.time.LocalDateTime localDateTime24 = reservation21.getStartTime();
        int int25 = reservation21.getEquipmentId();
        int int26 = reservation21.getUserId();
        int int27 = reservation21.getEquipmentId();
        sensor9.detach((model.SensorObserver) reservation21);
        org.junit.Assert.assertTrue("'" + equipmentStatus7 + "' != '" + model.enums.EquipmentStatus.ENABLED + "'", equipmentStatus7.equals(model.enums.EquipmentStatus.ENABLED));
        org.junit.Assert.assertNull(localDateTime24);
        org.junit.Assert.assertTrue("'" + int25 + "' != '" + 10 + "'", int25 == 10);
        org.junit.Assert.assertTrue("'" + int26 + "' != '" + 2 + "'", int26 == 2);
        org.junit.Assert.assertTrue("'" + int27 + "' != '" + 10 + "'", int27 == 10);
    }
}
