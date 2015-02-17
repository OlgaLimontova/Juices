package MyPackage.BSU;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.TreeMap;

class Exam {
    int grade;
    int key;
    Exam() {
        grade = 0;
        key = 0;
    }
    Exam(int grade, int key) {
        this.grade = grade;
        this.key = key;
    }
}

class Session {
    int session_year;
    int session_num;
    int last_exam_num;
    Exam[] exams = new Exam [20];
    Session() {
        session_year = 0;
        session_num = 0;
        last_exam_num = 0;
    }
    Session(int session_year, int session_num, int last_exam_num) {
        this.session_year = session_year;
        this.session_num = session_num;
        this.last_exam_num = last_exam_num;
    }
    void setLastExam(int last_exam_num) {
        this.last_exam_num = last_exam_num;
    }
    int getLastExam() {
        return last_exam_num;
    }
}

class RecordBook {
    int ID;
    int year_post;
    String name;
    int group;
    int course;
    int last_session_num;
    Session[] sessions = new Session[9];
    RecordBook() {
        ID = 0;
        year_post = 0;
        name = new String();
        group = 0;
        last_session_num = 0;
        course = 0;
    }
    RecordBook(int ID, int year_post, String name, int group, int last_session_num) {
        this.ID = ID;
        this.year_post = year_post;
        this.name = name;
        this.group = group;
        this.last_session_num = last_session_num;
    }
    void setID(int ID) {
        this.ID = ID;
        return;
    }
    void setYear_post(int year_post) {
        this.year_post = year_post;
        return;
    }
    void setName(String name) {
        this.name = name;
        return;
    }
    void setGroup(int group) {
        this.group = group;
        return;
    }
    void setCourse(int course) {
        this.course = course;
        return;
    }
    void setLast_session_num(int last_session_num) {
        this.last_session_num = last_session_num;
        return;
    }
    int getID() {
        return ID;
    }
    int getYear_post() {
        return year_post;
    }
    String getName() {
        return name;
    }
    int getGroup() {
        return group;
    }
    int getCourse() {
        return course;
    }
    int getLast_session_num() {
        return last_session_num;
    }
}

class Library {
    static int index;
    Library() {
        index = 0;
    }
    static void setIndex(int index1) {
        index = index1;
        return;
    }
    static int getIndex() {
        return index;
    }
    static TreeMap <String, Integer> subject_code = new TreeMap <String, Integer>();
    static TreeMap <Integer, String> code_subject = new TreeMap <Integer, String>();
    static TreeMap <Integer, Integer> id_index = new TreeMap <Integer, Integer>();
}

public class BSU {
    static ArrayList <RecordBook> recBooks = new ArrayList <RecordBook>();
    public int key = 0;
    void checkSubjects(String buf) { //проверяем, есть ли предмет buf
        if (Library.subject_code.containsKey(buf) == false) { //если предмета нет - добавляем его
            Library.subject_code.put(buf, Library.index);
            Library.code_subject.put(Library.index, buf);
            Library.setIndex(Library.getIndex() + 1);
        }
    }
    RecordBook newExam(RecordBook RB, int i, StringTokenizer st, String buf_str) { //добавляем экзамен в сессию i
        RB.sessions[i].exams[RB.sessions[i].getLastExam()] = new Exam();
        RB.sessions[i].exams[RB.sessions[i].getLastExam()].key = Library.subject_code.get(buf_str);
        RB.sessions[i].exams[RB.sessions[i].getLastExam()].grade = Integer.parseInt(st.nextToken());
        RB.sessions[i].setLastExam(RB.sessions[i].getLastExam() + 1);
        return RB;
    }
    RecordBook newSession(RecordBook RB, int i, int year, int num) { //добавляем сессию года year в зачётку
        RB.sessions[i] = new Session();
        RB.sessions[i].session_num = num;
        RB.sessions[i].session_year = year;
        return RB;
    }
    public void readData(FileReader file) { //считываем данные из файла
        BufferedReader br = new BufferedReader(file);
        try {
            while (br.ready()) {
                RecordBook A = new RecordBook();
                StringTokenizer st = new StringTokenizer(br.readLine());
                int k = 0;
                while (st.hasMoreTokens()) {
                    int buf = Integer.parseInt(st.nextToken());
                    for (RecordBook RB : recBooks)
                        if (RB.ID == buf) { //если есть студент с таким id
                            A = RB;
                            st.nextToken();
                            st.nextToken();
                            k++;
                        }
                    if (k == 0) { //если такого студента нет,
                        recBooks.add(A); //добавляем его в список
                        A.ID = buf;
                        Library.id_index.put(buf, key);
                        key++;
                        String buf1 = st.nextToken();
                        String buf2 = st.nextToken();
                        buf1 = buf1.concat(" ");
                        A.name = buf1.concat(buf2);
                    }
                }
                st = new StringTokenizer(br.readLine());
                A.course = Integer.parseInt(st.nextToken());
                A.group = Integer.parseInt(st.nextToken());
                int s_num = Integer.parseInt(st.nextToken());
                int s_year = Integer.parseInt(st.nextToken());
                int u = 0;
                for (int i = 0; i < A.getLast_session_num(); i++)
                    if ((A.sessions[i].session_num == s_num) && (A.sessions[i].session_year == s_year)) { //если нашли эту сессию
                        String buf_str = st.nextToken();
                        checkSubjects(buf_str);
                        A = newExam(A, i, st, buf_str);
                        u++;
                    }
                if (u == 0) { //если такой сессии нет,
                    A = newSession(A, A.getLast_session_num(), s_year, s_num); //создаём её
                    String buf_str = st.nextToken();
                    checkSubjects(buf_str);
                    A = newExam(A, A.getLast_session_num(), st, buf_str); //добавляем в сессию экзамен
                    A.setLast_session_num(A.getLast_session_num() + 1);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void work() {
        Graphics g = new Graphics();
        g.launchFrame();
        g.action();
    }
}

class Graphics {
    private JTextField field;
    private JPanel panel;
    private JFrame frame;
    private JButton all;
    private JButton byName;
    private JButton byID;
    private JButton newRecordbook;
    private JButton changeRecordbook;
    private JButton averageGrade;
    private JButton excellents;
    private JButton underachievers;
    private JButton information;
    public Graphics() {
        field = new JTextField(5);
        panel = new JPanel();
        frame = new JFrame("Students database");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        all = new JButton("Show all information");
        all.setBackground(new Color(250, 250, 150));
        byName = new JButton("Show by name (input name)");
        byName.setBackground(new Color(186, 250, 134));
        byID = new JButton("Show by ID (input ID)");
        byID.setBackground(new Color(250, 250, 150));
        newRecordbook = new JButton("Create new recordbook (input ID, name, course, group)");
        newRecordbook.setBackground(new Color(186, 250, 134));
        changeRecordbook = new JButton("Change recordbook (input ID, name, course, group)");
        changeRecordbook.setBackground(new Color(250, 250, 150));
        averageGrade = new JButton("Average grade (input number, year)");
        averageGrade.setBackground(new Color(186, 250, 134));
        excellents = new JButton("Show excellents (input number, year)");
        excellents.setBackground(new Color(250, 250, 150));
        underachievers = new JButton("Show underachievers (input number, year)");
        underachievers.setBackground(new Color(186, 250, 134));
        information = new JButton("Information about session (input number, year)");
        information.setBackground(new Color(250, 250, 150));
    }
    public void launchFrame() {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension dim = kit.getScreenSize();
        frame.setLayout(new BorderLayout());
        panel.add(byID);
        panel.add(byName);
        panel.add(all);
        panel.add(averageGrade);
        panel.add(changeRecordbook);
        panel.add(newRecordbook);
        panel.add(information);
        panel.add(underachievers);
        panel.add(excellents);
        panel.setLayout(new GridLayout(3, 3));
        frame.setLocation(dim.width / 2 - 500, dim.height / 2 - 100);
        frame.add(panel, BorderLayout.SOUTH);
        frame.add(field, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    public void action() {
        all.addActionListener(new ActionShowAll(field));
        byName.addActionListener(new ActionShowName(field));
        byID.addActionListener(new ActionShowID(field));
        changeRecordbook.addActionListener(new ActionEdit(field));
        newRecordbook.addActionListener(new ActionNew(field));
        information.addActionListener(new ActionInformation(field));
        excellents.addActionListener(new ActionExcellents(field));
        underachievers.addActionListener(new ActionUnderachievers(field));
        averageGrade.addActionListener(new ActionAverageGrade(field));
    }
    public void table(String[][] data, String[] names) {
        JFrame frame = new JFrame("Show");
        JTable myTable = new JTable(data, names);
        JScrollPane sp = new JScrollPane(myTable);
        frame.getContentPane().add(sp);
        frame.pack();
        myTable.setBackground(new Color(237, 250, 196));
        frame.setVisible(true);
        frame.setSize(new Dimension(850, 301));
        frame.setLocationRelativeTo(null);
    }
}

class ActionShowAll implements ActionListener {
    private JTextField field;
    ActionShowAll(JTextField field) {
        this.field = field;
    }
    @Override
    public void actionPerformed(ActionEvent arg0) {
        BSU B = new BSU();
        String[] names = {"ID", "Name", "Course", "Group", "Session num", "Session year", "Subject", "Grade"};
        String[][] data = new String[15][15];
        int m = 0;
        int k = 0;
        for (RecordBook J: B.recBooks)
            for (int i = 0; i < J.getLast_session_num(); i++)
                for (int j = 0; j < J.sessions[i].getLastExam(); j++) {
                    Integer r = (Integer)J.ID;
                    data[m][k++] = "             " + r.toString();
                    data[m][k++] = J.name;
                    r = (Integer)J.course;
                    data[m][k++] = "                " + r.toString();
                    r = (Integer)J.group;
                    data[m][k++] = "                " + r.toString();
                    r = (Integer)J.sessions[i].session_num;
                    data[m][k++] = "                " + r.toString();
                    r = (Integer)J.sessions[i].session_year;
                    data[m][k++] = "              " + r.toString();
                    String g = Library.code_subject.get(J.sessions[i].exams[j].key);
                    data[m][k++] = "   " + g;
                    r = (Integer)J.sessions[i].exams[j].grade;
                    data[m++][k] = "               " + r.toString();
                    k = 0;
                }
        Graphics A = new Graphics();
        A.table(data, names);
    }
}

class ActionShowName implements ActionListener {
    private JTextField field;
    ActionShowName(JTextField field) {
    this.field = field;
}
    @Override
    public void actionPerformed(ActionEvent arg0) {
        String h = field.getText();
        BSU B = new BSU();
        String[] names = {"ID", "Name", "Course", "Group", "Session num", "Session year", "Subject", "Grade"};
        String[][] data = new String[5][10];
        int k = 0;
        int m = 0;
        for (RecordBook J: B.recBooks)
            if (h.compareTo(J.name) == 0)
                for (int i = 0; i < J.getLast_session_num(); i++)
                    for (int j = 0; j < J.sessions[i].getLastExam(); j++) {
                        Integer r = (Integer)J.ID;
                        data[m][k++] = "             " + r.toString();
                        data[m][k++] = J.name;
                        r = (Integer)J.course;
                        data[m][k++] = "                " + r.toString();
                        r = (Integer)J.group;
                        data[m][k++] = "                " + r.toString();
                        r = (Integer)J.sessions[i].session_num;
                        data[m][k++] = "                " + r.toString();
                        r = (Integer)J.sessions[i].session_year;
                        data[m][k++] = "              " + r.toString();
                        String g = Library.code_subject.get(J.sessions[i].exams[j].key);
                        data[m][k++] = "   " + g;
                        r = (Integer)J.sessions[i].exams[j].grade;
                        data[m++][k] = "               " + r.toString();
                        k = 0;
                    }
        Graphics A = new Graphics();
        A.table(data, names);
    }
}

class ActionShowID implements ActionListener {
    private JTextField field;
    public ActionShowID(JTextField field) {
        this.field = field;
    }
    @Override
    public void actionPerformed(ActionEvent arg0) {
        String h = field.getText();
        int l = Integer.parseInt(h);
        String[] names = {"ID", "Name", "Course", "Group", "Session num", "Session year", "Subject", "Grade"};
        String[][] data = new String[5][10];
        BSU B = new BSU();
        int k = 0;
        int m = 0;
        for (RecordBook J: B.recBooks)
            if (l == J.ID)
                for (int i = 0; i < J.getLast_session_num(); i++)
                    for (int j = 0; j < J.sessions[i].getLastExam(); j++) {
                        Integer r = (Integer)J.ID;
                        data[m][k++] = "             " + r.toString();
                        data[m][k++] = J.name;
                        r = (Integer)J.course;
                        data[m][k++] = "                " + r.toString();
                        r = (Integer)J.group;
                        data[m][k++] = "                " + r.toString();
                        r = (Integer)J.sessions[i].session_num;
                        data[m][k++] = "                " + r.toString();
                        r = (Integer)J.sessions[i].session_year;
                        data[m][k++] = "              " + r.toString();
                        String g = Library.code_subject.get(J.sessions[i].exams[j].key);
                        data[m][k++] = "   " + g;
                        r = (Integer)J.sessions[i].exams[j].grade;
                        data[m++][k] = "               " + r.toString();
                        k = 0;
                    }
        Graphics A = new Graphics();
        A.table(data, names);
    }
}

class ActionAverageGrade implements ActionListener {
    private JTextField field;
    ActionAverageGrade(JTextField field) {
        this.field = field;
    }
    @Override
    public void actionPerformed(ActionEvent arg0) {
        String h = field.getText();
        StringTokenizer st = new StringTokenizer(h);
        int session_num = Integer.parseInt(st.nextToken());
        int session_year = Integer.parseInt(st.nextToken());
        BSU B = new BSU();
        String[] names = {"Subject", "Grade"};
        String[][] data = new String[15][15];
        int u = 0;
        int m = 0;
        for (int i = 0; i < Library.getIndex(); i++) {
            double sum = 0.0;
            int number = 0;
            int s = 0;
            for (RecordBook V: B.recBooks)
                for (int j = 0; j < V.getLast_session_num(); j++)
                    if ((V.sessions[j].session_num == session_num) && (V.sessions[j].session_year == session_year)) {
                        for (int g = 0; g < V.sessions[j].getLastExam(); g++)
                            if ((V.sessions[j].exams[g].key == i) && (V.sessions[j].exams[g].grade >= 0) &&
                                    (V.sessions[j].exams[g].grade < 11)) {
                                s++;
                                sum += V.sessions[j].exams[g].grade;
                                number++;
                            }
                    }
            if (s != 0) {
                double res = sum / number;
                String buf = "";
                for (int f = 0; f < 55; f++)
                    buf += " ";
                data[m][u++] = buf + Library.code_subject.get(i);
                buf = "";
                for (int f = 0; f < 65; f++)
                    buf += " ";
                Double r = res;
                data[m++][u] = buf + r.toString();
            }
            u = 0;
        }
        Graphics g = new Graphics();
        g.table(data, names);
    }
}

class ActionEdit implements ActionListener {
    private JTextField field;
    private String name;
    private int ID;
    private int course;
    private int group;
    public ActionEdit(JTextField field) {
        this.field = field;
    }
    BSU B = new BSU();
    @Override
    public void actionPerformed(ActionEvent arg0) {
        String h = field.getText();
        StringTokenizer st = new StringTokenizer(h);
        ID = Integer.parseInt(st.nextToken());
        name = st.nextToken();
        name.concat(" ");
        name.concat(st.nextToken());
        course = Integer.parseInt(st.nextToken());
        group = Integer.parseInt(st.nextToken());
        for (RecordBook V: B.recBooks)
            if (V.ID == ID) {
                V.name = name;
                V.group = group;
                V.course = course;
                String[] names = {"ID", "Name", "Course", "Group", "Session num", "Session year", "Subject", "Grade"};
                String[][] data = new String[15][15];
                BSU C = new BSU();
                int m = 0;
                int k = 0;
                for (RecordBook J: C.recBooks)
                    for (int i = 0; i < J.getLast_session_num(); i++)
                        for (int j = 0; j < J.sessions[i].getLastExam(); j++) {
                            Integer r = (Integer)J.ID;
                            data[m][k++] = "             " + r.toString();
                            data[m][k++] = J.name;
                            r = (Integer)J.course;
                            data[m][k++] = "                " + r.toString();
                            r = (Integer)J.group;
                            data[m][k++] = "                " + r.toString();
                            r = (Integer)J.sessions[i].session_num;
                            data[m][k++] = "                " + r.toString();
                            r = (Integer)J.sessions[i].session_year;
                            data[m][k++] = "              " + r.toString();
                            String g = Library.code_subject.get(J.sessions[i].exams[j].key);
                            data[m][k++] = "   " + g;
                            r = (Integer)J.sessions[i].exams[j].grade;
                            data[m++][k] = "               " + r.toString();
                            k = 0;
                        }
                Graphics A = new Graphics();
                A.table(data, names);
            }
    }
}

class ActionNew implements ActionListener {
    private JTextField field;
    private String name;
    private int ID;
    private int course;
    private int group;
    public ActionNew(JTextField field) {
        this.field = field;
    }
    @Override
    public void actionPerformed(ActionEvent arg0) {
        String h = field.getText();
        StringTokenizer st = new StringTokenizer(h);
        int u = 0;
        BSU B = new BSU();
        RecordBook A = new RecordBook();
        ID = Integer.parseInt(st.nextToken());
        name = st.nextToken();
        name = name.concat(" ");
        String yo = st.nextToken();
        name = name.concat(yo);
        course = Integer.parseInt(st.nextToken());
        group = Integer.parseInt(st.nextToken());
        for (RecordBook V: B.recBooks)
            if (V.ID == ID) {
                A = V;
                u++;
            }
        if (u == 0) {
            B.recBooks.add(A);
            A.ID = ID;
            Library.id_index.put(ID, B.key);
            B.key++;
            A.name = name;
        }
        A.course = course;
        A.group = group;
        BSU C = new BSU();
        String[] names = {"ID", "Name", "Course", "Group", "Session num", "Session year", "Subject", "Grade"};
        String[][] data = new String[15][15];
        int m = 0;
        int k = 0;
        for (RecordBook J: C.recBooks)
            for (int i = 0; i < J.getLast_session_num(); i++)
                for (int j = 0; j < J.sessions[i].getLastExam(); j++) {
                    Integer r = (Integer)J.ID;
                    data[m][k++] = "             " + r.toString();
                    data[m][k++] = J.name;
                    r = (Integer)J.course;
                    data[m][k++] = "                " + r.toString();
                    r = (Integer)J.group;
                    data[m][k++] = "                " + r.toString();
                    r = (Integer)J.sessions[i].session_num;
                    data[m][k++] = "                " + r.toString();
                    r = (Integer)J.sessions[i].session_year;
                    data[m][k++] = "              " + r.toString();
                    String g = Library.code_subject.get(J.sessions[i].exams[j].key);
                    data[m][k++] = "   " + g;
                    r = (Integer)J.sessions[i].exams[j].grade;
                    data[m++][k] = "               " + r.toString();
                    k = 0;
                }
        Integer r = (Integer)A.ID;
        data[m][k++] = "             " + r.toString();
        data[m][k++] = name;
        r = (Integer)A.course;
        data[m][k++] = "                " + r.toString();
        r = (Integer)A.group;
        data[m][k++] = "                " + r.toString();
        data[m][k++] = "                -";
        data[m][k++] = "                -";
        data[m][k++] = "                -";
        data[m][k] = "                -";
        Graphics G = new Graphics();
        G.table(data, names);
    }
}

class ActionInformation implements ActionListener {
    private JTextField field;
    ActionInformation(JTextField field) {
        this.field = field;
    }
    @Override
    public void actionPerformed(ActionEvent arg0) {
        String h = field.getText();
        StringTokenizer st = new StringTokenizer(h);
        int session_num = Integer.parseInt(st.nextToken());
        int session_year = Integer.parseInt(st.nextToken());
        BSU B = new BSU();
        String[] names = {"Name", "Subject", "Grade"};
        String[][] data = new String[15][15];
        int u = 0;
        int m = 0;
        for (RecordBook V: B.recBooks)
            for (int i = 0; i < V.getLast_session_num(); i++)
                if ((V.sessions[i].session_num == session_num) && (V.sessions[i].session_year == session_year))
                    for (int j = 0; j < V.sessions[i].getLastExam(); j++) {
                        data[m][u++] = "                                   " + V.name;
                        data[m][u++] = "                                    " + Library.code_subject.get(V.sessions[i].exams[j].key);
                        Integer r = (Integer)V.sessions[i].exams[j].grade;
                        data[m][u] = "                                           " + r.toString();
                        m++;
                        u = 0;
                    }
        Graphics g = new Graphics();
        g.table(data, names);
    }
}

class ActionExcellents implements ActionListener {
    private JTextField field;
    ActionExcellents(JTextField field) {
        this.field = field;
    }
    @Override
    public void actionPerformed(ActionEvent arg0) {
        String h = field.getText();
        StringTokenizer st = new StringTokenizer(h);
        int session_num = Integer.parseInt(st.nextToken());
        int session_year = Integer.parseInt(st.nextToken());
        BSU B = new BSU();
        String[] names = {"Name"};
        String[][] data = new String[15][15];
        int u = 0;
        int l = 0;
        for (RecordBook V: B.recBooks) {
            int k = 0;
            int m = 0;
            for (int i = 0; i < V.getLast_session_num(); i++)
                if ((V.sessions[i].session_num == session_num) && (V.sessions[i].session_year == session_year)) {
                    m++;
                    for (int j = 0; j < V.sessions[i].getLastExam(); j++)
                        if (V.sessions[i].exams[j].grade < 9)
                            k++;
                }
            if (m != 0 && k == 0) {
                String buf = "";
                for (int f = 0; f < 125; f++)
                    buf += " ";
                data[l++][u] = buf + V.name;
                m++;
                u = 0;
            }
        }
        Graphics g = new Graphics();
        g.table(data, names);
    }
}

class ActionUnderachievers implements ActionListener {
    private JTextField field;
    ActionUnderachievers(JTextField field) {
        this.field = field;
    }
    @Override
    public void actionPerformed(ActionEvent arg0) {
        String h = field.getText();
        StringTokenizer st = new StringTokenizer(h);
        int session_num = Integer.parseInt(st.nextToken());
        int session_year = Integer.parseInt(st.nextToken());
        BSU B = new BSU();
        String[] names = {"Name"};
        String[][] data = new String[15][15];
        int u = 0;
        int l = 0;
        for (RecordBook V: B.recBooks) {
            int k = 0;
            int m = 0;
            for (int i = 0; i < V.getLast_session_num(); i++) {
                m++;
                if ((V.sessions[i].session_num == session_num) && (V.sessions[i].session_year == session_year))
                    for (int j = 0; j < V.sessions[i].getLastExam(); j++)
                        if (V.sessions[i].exams[j].grade < 4)
                            k++;
            }
            if (m != 0 && k != 0) {
                String buf = "";
                for (int f = 0; f < 130; f++)
                    buf += " ";
                data[l++][u] = buf + V.name;
                u = 0;
            }
        }
        Graphics g = new Graphics();
        g.table(data, names);
    }
}