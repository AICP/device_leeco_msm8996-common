#!/system/bin/sh

chmod 666 /sys/module/lowmemorykiller/parameters/minfree
chown root /sys/module/lowmemorykiller/parameters/minfree

echo "29028,38704,48380,106436,145140,154816" > /sys/module/lowmemorykiller/parameters/minfree

echo "2:2342400" > /sys/module/msm_performance/parameters/cpu_max_freq
echo "3:2342400" > /sys/module/msm_performance/parameters/cpu_max_freq
echo "0:2188800" > /sys/module/msm_performance/parameters/cpu_max_freq
echo "1:2188800" > /sys/module/msm_performance/parameters/cpu_max_freq