package com.alex.vestlist.bussiness;

import com.alex.vestlist.model.Doubt;
import com.alex.vestlist.model.ExerciseList;
import com.alex.vestlist.model.Teacher;

import java.util.List;

/**
 * Created by Alex on 16/03/2017.
 */

interface ListBusInterface {

    /**
     * Carrega máterias com paginação.
     * @param offset - total já carregado
     * @param limit
     * @return
     */
    public List loadSubjects(int offset, int limit);
    public List loadTeachers(int offset, int limit);
    public List loadDoubts(int offset, int limit);

    public boolean removeList(long id);
    public boolean removeDoubt(long id);

    public long insertList(ExerciseList list);
    public long insertDoubt(Doubt doubt);

    public long updateDoubt(Doubt doubt);
    public long updateList(ExerciseList list);

    /**
     * Calcula a porcentagem de listas de um professor que estão com status true.
     * @return a porcentagem.
     */
    public float getListCompletedPercent(Teacher teacher);

}
