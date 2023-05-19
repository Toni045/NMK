import { useContext, useEffect, useState } from "react";
import { UserDTO } from "../../api";
import { ClientsContext } from "../../store/ClientsContext";
import UsersTableComponent from "./UsersTableComponent";

function UsersTableState() {
    const [users, setUsers] = useState<Array<UserDTO>>([]);
    const [newUserType, setNewUserType] = useState<number | undefined>(undefined);
    const [currentEditingIndex, setCurrentEditingIndex] = useState<number | undefined>(undefined);
    const { userClient } = useContext(ClientsContext);

    function onUpdate(index: number) {
        setCurrentEditingIndex(index);
        setNewUserType(users[index].userTypeId);
    }

    function cancel() {
        setNewUserType(undefined);
        setCurrentEditingIndex(undefined);
    }

    async function submitNewRow() {
        if (currentEditingIndex !== undefined) {
            users[currentEditingIndex!] = await userClient.updateUserRole({ userId: users[currentEditingIndex].id!, typeId: newUserType! });
        }
        cancel()
        setUsers([...users]);
    }

    async function getParams() {
        setUsers(await userClient.getAllUsers());
    }

    useEffect(() => {
        getParams();
    }, []);

    return <UsersTableComponent
        users={users}
        cancel={cancel}
        currentEditingIndex={currentEditingIndex}
        newUserType={newUserType}
        onChange={setNewUserType}
        onUpdate={onUpdate}
        submit={submitNewRow} />
}

export default UsersTableState;