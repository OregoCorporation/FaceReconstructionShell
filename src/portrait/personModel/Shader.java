package portrait.personModel;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;


/**
 * @author Igor Gulkin 16.04.2018
 *
 * Класс Shader создает из шейдерную программу и хранит её в себе,
 * Эта программа будет непосредственно использоваться OpenGL
 * для отрисовки графического изображения.
 */

public final class Shader {

    private static final Logger LOG = Logger.getLogger(Shader.class.getName());

    /**
     * Поле isReady указывает готовность сборки данного шейдера.
     */

    private boolean isReady;

    /**
     * Поля vertexShaderSource и fragmentShaderSource хранят в себе
     * шейдерные программы в виде одной строки.
     */

    private String vertexShaderSource;

    private String fragmentShaderSource;

    /**
     * Поле program хранит шейдерную программу,
     * к которой OpenGL будет обращаться через тип int.
     */

    private int program;

    /**
     * Конструктор при помощи OpenGL собирает шейдерную программу
     *
     * @param vertexShaderPath   указывает путь к файлу вершинного шейдера
     * @param fragmentShaderPath указывает путь к файлу фрагментного шейдера.
     */

    public Shader(final String vertexShaderPath, final String fragmentShaderPath) {
        //Подключаемся к шейдерам:
        try {
            //Преобразуем в строку:
            this.vertexShaderSource = this.loadShader(vertexShaderPath);
            this.fragmentShaderSource = this.loadShader(fragmentShaderPath);

            //Дальше идет работа с OpenGL (когда перенесете на Android раскомментируйте эти строчки):
            int success, errors;
            char infoLog[];

            ////Вершинный шейдер:
            //int vertexProgram = glCreateShader(GL_VERTEX_SHADER);
            //glShaderSource(vertexProgram, 1, vertexShaderSource, NULL);
            //glCompileShader(vertexProgram);
            ////Проеверяем компиляцию:
            //glGetShaderiv(vertexProgram, GL_COMPILE_STATUS, success);
            //if (!success){
            //    glGetShaderInfoLog(vertexProgram, 512, NULL, infoLog);
            //    LOG.info("ERROR_VERTEX_SHADER" + infoLog.toString());
            //}

            ////Фрагментный шейдер:
            //int fragmentProgram = glCreateShader(GL_FRAGMENT_SHADER);
            //glShaderSource(fragmentProgram, 1, fragmentShaderSource, NULL);
            //glCompileShader(fragmentShaderSource);
            ////Проеверяем компиляцию:
            //glGetShaderiv(fragmentProgram, GL_COMPILE_STATUS, success);
            //if (!success){
            //    glGetShaderInfoLog(fragmentProgram, 512, NULL, infoLog);
            //    LOG.info("ERROR_FRAGMENT_SHADER" + infoLog.toString());
            //}

            ////Шейдерная программа:
            //this.program = glCreateProgram();
            //glAttachShader(this.program, vertex);
            //glAttachShader(this.program, fragment);
            //glLinkProgram(this.program);
            ////Проеверяем сборку программы:
            //glGetProgramiv(this.program, GL_LINK_STATUS, success);
            //if (!success) {
            //    glGetProgramInfoLog(this.program, 512, NULL, infoLog);
            //    LOG.info("ERROR_PROGRAM_SHADER" + infoLog.toString());
            //}

            ////Удаляем шейдеры:
            //glDeleteShader(vertexProgram);
            //glDeleteShader(fragmentProgram);
            this.isReady = true;
        } catch (final FileNotFoundException e) {
            this.isReady = false;
            this.vertexShaderSource = "NULL";
            this.fragmentShaderSource = "NULL";
            LOG.info("Не удалось найти указанные шейдеры");
        }
    }

    /**
     * @param shaderPath указывает путь к файлу
     * @return шейдерную программу в виде строки
     * @throws FileNotFoundException, если путь к файлу не найден.
     */

    private String loadShader(final String shaderPath) throws FileNotFoundException {
        final BufferedReader bufferedReader = new BufferedReader(new FileReader(shaderPath));
        final List<String> shaderStrings = bufferedReader.lines().collect(Collectors.toList());
        return convertToString(shaderStrings);
    }

    /**
     * convertToString() преобразует массив одну строку
     *
     * @param shaderStrings указывает массив строк List<String>
     * @return строку.
     */

    private String convertToString(final List<String> shaderStrings) {
        final StringBuilder stringBuilder = new StringBuilder();
        for (final String shaderString : shaderStrings) {
            stringBuilder.append(shaderString);
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }


    /**
     * @return готовность шейдерной программы.
     */

    public final boolean isReady() {
        return isReady;
    }

    /**
     * use() - указывает OpenGL использовать текущую программу.
     */

    public final void use() {
        ////Раскомментируйте эту строчку, когда подключите OpenGL:
        //glUseProgram(this.program)
    }

    /**
     * toString() разработан для отладки программы,
     * если Вам нужно посмотреть исходный код шейдеров.
     *
     * @return исходный код шейдеров. Примечание: этот метод разработан для вывода на консоль.
     * Используйте System.out.println(), JUL, Log4j or slf4j.
     */

    @Override
    public final String toString() {
        return "\nVertex shader:\n\n" + vertexShaderSource + "\nFragment shader\n\n" + fragmentShaderSource;
    }
}