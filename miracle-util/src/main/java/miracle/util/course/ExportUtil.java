package miracle.util.course;


import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;

public class ExportUtil {
    public static void export(String fileName, List<Course> courseList) throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.newDocument();
        document.setXmlStandalone(true);
        Element root = document.createElement("courses");
        for (int i = 0; i < courseList.size(); i++) {
            Course c = courseList.get(i);
            Element course = document.createElement("course");
            Element id = document.createElement("courseId"),
                    name = document.createElement("courseName"),
                    num = document.createElement("courseNum"),
                    teacher = document.createElement("courseTeacher");
            course.setAttribute("id", id + "");
            name.setTextContent(c.getCourseName());
            num.setTextContent(c.getCourseNum());
            teacher.setTextContent(c.getTeacher());

            course.appendChild(id);
            course.appendChild(name);
            course.appendChild(num);
            course.appendChild(teacher);
            root.appendChild(course);
        }
        document.appendChild(root);
        File file = new File("course.xml");
        TransformerFactory tff = TransformerFactory.newInstance();
        Transformer tf = tff.newTransformer();
        tf.setOutputProperty(OutputKeys.INDENT, "yes");
        tf.transform(new DOMSource(document), new StreamResult(file));
    }
}
