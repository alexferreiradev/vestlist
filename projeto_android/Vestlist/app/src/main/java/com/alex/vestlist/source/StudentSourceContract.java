package com.alex.vestlist.source;

import android.content.ContentValues;

import com.alex.vestlist.model.Doubt;
import com.alex.vestlist.model.ExerciseList;
import com.alex.vestlist.model.Subject;
import com.alex.vestlist.model.Teacher;

import java.util.List;

/**
 * Created by Alex on 16/03/2017.
 *
 * Contract between source e Dao
 */

interface StudentSourceContract {

    public interface Dao<ModelType> {

        public ModelType get(long id);

        public long save(ModelType object);

        /**
         * Apaga o objeto com id = id
         * @param id
         * @return - número de linhas alterado. Se == 1, ok.
         */
        public int delete(long id);

        public List<ModelType> search(String key, String value, int offset, int limit);

        /**
         * Carrega um lista de objetos, com a restrição de quantidade por carregamento. Serve para paginação.
         * @param offset - total já carregado
         * @param limit - total a ser carregado
         * @return - lista com total de itens = limit.
         */
        public List<ModelType> load(int offset, int limit);

        public int update(long id, ContentValues values);

        /**
         * Faz join entre listas e duvidas para retornar total de duvidas por lista
         * @return
         * @param offset
         * @param limit
         */
        public List<ExerciseList> getExerciseListWithDoubt(int offset, int limit, long teacherId);
    }

    /**
     * Carrega máterias com paginação.
     * @param offset - total já carregado
     * @param limit - Não pode ser negativo
     * @return
     */
    public List loadSubjects(int offset, int limit);
    public List loadTeachers(Subject subject, int offset, int limit);
    public List loadDoubts(ExerciseList list, int offset, int limit);
    public List loadLists(Teacher teacher, int offset, int limit);

    public boolean removeList(long id);
    public boolean removeDoubt(long id);

    public long insertList(ExerciseList list);
    public long insertDoubt(Doubt doubt);

    public int updateDoubt(Doubt doubt);
    public int updateList(ExerciseList list);

    /**
     * Calcula a porcentagem de listas de um professor que estão com status true.
     * @return a porcentagem.
     */
    public float getListCompletedPercent(Teacher teacher);

}
