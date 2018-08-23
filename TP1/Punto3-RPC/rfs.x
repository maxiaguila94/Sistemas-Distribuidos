/* rfs.x */
typedef opaque file_data<>;
struct open_record
{
    string file_name<>;
    int flags;
};
struct read_record
{
    int fd;
    int count;
};

struct write_record
{
    int fd;
    int count;
    string buffer<>;
};

program RFS
{
    version RFS_VERS_1
    {
        int RFS_OPEN(open_record r) = 1;
        file_data RFS_READ(read_record r) = 2;
        file_data RFS_WRITE(write_record) = 3;
        int RFS_CLOSE(int fd) = 4;
    } = 1;
} = 0x20000001;